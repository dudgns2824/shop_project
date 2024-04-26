package com.dudgns.auth.user.service;

import com.dudgns.auth.config.security.JwtTokenProvider;
import com.dudgns.auth.entity.UserEntity;
import com.dudgns.auth.entity.UserTokenEntity;
import com.dudgns.auth.entity.id.UserTokenId;
import com.dudgns.auth.entity.redis.AccessTokenEntity;
import com.dudgns.auth.entity.redis.MailRequestEntity;
import com.dudgns.auth.enums.RolesEnum;
import com.dudgns.auth.exception.*;
import com.dudgns.auth.repository.UserRepository;
import com.dudgns.auth.repository.UserTokenRepository;
import com.dudgns.auth.repository.redis.MailRequestRepository;
import com.dudgns.auth.repository.redis.AccessTokenRepository;
import com.dudgns.auth.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccessTokenRepository accessTokenRepository;
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
                        .userGUID(userGUID)
                        .refreshToken(token)
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

    public ResponseUserLoginDto loginUser(String userId, String password) {
        UserEntity user = null;


        try {
            if (userId != null && !userId.equals("")) {
                user = userRepository.findUserByUserId(userId);
            }

            if (passwordEncoder.matches(password, user.getPassword())) {
                Optional<AccessTokenEntity> accessTokenEntityOptional = accessTokenRepository.findById(user.getUserGUID());

                AccessTokenEntity accessTokenEntity = null;

                if (accessTokenEntityOptional.isPresent()) {
                    accessTokenEntity = accessTokenEntityOptional.get();
                } else {
                    accessTokenEntity = accessTokenRepository.save(AccessTokenEntity.builder()
                            .accessToken(jwtTokenProvider.createToken(user))
                            .userGUID(user.getUserGUID())
                            .expire(900L)
                            .build());
                }

                Optional<UserTokenEntity> userTokenEntityOptional = userTokenRepository.findByUserGuid(user.getUserGUID());

                String token = jwtTokenProvider.createToken(user);

                if (userTokenEntityOptional.isPresent()) {
                    UserTokenEntity userTokenEntity = userTokenEntityOptional.get();
                    userTokenEntity.getId().setRefreshToken(token);
                    userTokenRepository.save(userTokenEntity);
                } else {
                    userTokenRepository.save(UserTokenEntity.builder()
                            .id(UserTokenId.builder()
                                    .userGUID(user.getUserGUID())
                                    .refreshToken(token)
                                    .build()
                            )
                            .expireDate(LocalDateTime.now().plus(1, ChronoUnit.DAYS))
                            .build());
                }


                return ResponseUserLoginDto.builder()
                        .user(UserLoginDto.builder()
                                .userId(user.getUserId())
                                .userGUID(user.getUserGUID())
                                .email(user.getUserEmail())
                                .build())
                        .accessToken(accessTokenEntity.getAccessToken())
                        .build();
            } else {
                throw new LoginFailedException();
            }
        } catch (Exception e) {
            throw new LoginFailedException();
        }

    }

}
