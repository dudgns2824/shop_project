package com.dudgns.auth.user.service;

import com.dudgns.auth.config.security.JwtTokenProvider;
import com.dudgns.auth.entity.UserEntity;
import com.dudgns.auth.entity.UserTokenEntity;
import com.dudgns.auth.entity.id.UserTokenId;
import com.dudgns.auth.entity.redis.MailRequestEntity;
import com.dudgns.auth.entity.redis.RefreshTokenEntity;
import com.dudgns.auth.enums.RolesEnum;
import com.dudgns.auth.exception.*;
import com.dudgns.auth.repository.UserRepository;
import com.dudgns.auth.repository.UserTokenRepository;
import com.dudgns.auth.repository.redis.MailRequestRepository;
import com.dudgns.auth.repository.redis.RefreshTokenRepository;
import com.dudgns.auth.user.dto.*;
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

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            Optional<RefreshTokenEntity> refreshTokenEntityOptional = refreshTokenRepository.findById(user.getUserGUID());

            RefreshTokenEntity refreshTokenEntity = null;

            if (refreshTokenEntityOptional.isPresent()) {
                refreshTokenEntity = refreshTokenEntityOptional.get();
            } else {
                refreshTokenEntity = refreshTokenRepository.save(RefreshTokenEntity.builder()
                        .refreshToken(jwtTokenProvider.createToken(user))
                        .userGUID(user.getUserGUID())
                        .build());
            }

            return ResponseUserLoginDto.builder()
                    .user(UserLoginDto.builder()
                            .userId(user.getUserId())
                            .userGUID(user.getUserGUID())
                            .email(user.getUserEmail())
                            .build())
                    .refreshToken(refreshTokenEntity.getRefreshToken())
                    .build();
        } else {
            throw new LoginFailedException();
        }
    }

}
