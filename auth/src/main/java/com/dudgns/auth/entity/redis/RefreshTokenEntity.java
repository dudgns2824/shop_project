package com.dudgns.auth.entity.redis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Builder
@Getter
@Setter
@RedisHash(value = "refreshToken")
public class RefreshTokenEntity {
    @Id
    private UUID userGUID;
    private String refreshToken;
}
