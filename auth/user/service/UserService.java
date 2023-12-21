package com.dudgns.backendauth.user.service;

import com.dudgns.backendauth.config.security.JwtTokenProvider;
import com.dudgns.backendauth.entity.UserEntity;
import com.dudgns.backendauth.entity.UserTokenEntity;
import com.dudgns.backendauth.entity.id.UserTokenId;
import com.dudgns.backendauth.entity.redis.MailRequestEntity;
import com.dudgns.backendauth.entity.redis.RefreshTokenEntity;
import com.dudgns.backendauth.enums.RolesEnum;
import com.dudgns.backendauth.exception.*;
import com.dudgns.backendauth.repository.UserRepository;
import com.dudgns.backendauth.repository.UserTokenRepository;
import com.dudgns.backendauth.repository.redis.MailRequestRepository;
import com.dudgns.backendauth.repository.redis.RefreshTokenRepository;
import com.dudgns.backendauth.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MailRequestRepository mailRequestRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseCreateUserDto create(RequestCreateUserDto dto) {
        UUID userGUID = UUID.randomUUID();

        String email = dto.getEmail();

        if (userRepository.existsByUserEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        Optional<MailRequestEntity> mailRequestOptional = mailRequestRepository.findById(email);

        if (!mailRequestOptional.isPresent() || !mailRequestOptional.get().isVerified()) {
            throw new NotVerifiedException();
        }

        String password = dto.getPassword();

        if (password == null) {
            throw new PasswordNotEnteredException();
        } else if (password != null && (password.length() < 8 || password.length() > 16)) {
            throw new PasswordRuleException();
        }

        String encodePassword = passwordEncoder.encode(password);

        UserEntity userEntity = UserEntity.builder()
                .UserGUID(userGUID)
                .UserId(dto.getUserId())
                .UserEmail(dto.getEmail())
                .password(encodePassword)
                .role(RolesEnum.USER.getValue())
                .build();

        userRepository.saveAndFlush(userEntity);

        String token = jwtTokenProvider.createToken(userEntity);

        userTokenRepository.save(UserTokenEntity.builder()
                .id(UserTokenId.builder()
                        .UserGUID(userGUID)
                        .AccessToken(token)
                        .build()
                )
                .expireDate(LocalDateTime.now().plus(1, ChronoUnit.DAYS))
                .build());

        return ResponseCreateUserDto.builder()
                .userId(dto.getUserId())
                .userGUID(userGUID)
                .email(dto.getEmail())
                .role(RolesEnum.USER.getValue())
                .build();
    }

    public ResponseUserLoginDto loginUser(RequestUserLoginDto dto) {

        String userId = dto.getUserId();
        UserEntity user = null;

        if (userId != null && !userId.equals("")) {
            user = userRepository.findUserByUserId(userId);
        } else {
            throw new LoginFailedException();
        }

        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(user.getUserGUID()).get();

        return ResponseUserLoginDto.builder()
                .user(UserLoginDto.builder()
                        .userId(user.getUserId())
                        .userGUID(user.getUserGUID())
                        .email(user.getUserEmail())
                        .build())
                .refreshToken(refreshTokenEntity.getRefreshToken())
                .build();
    }

}
