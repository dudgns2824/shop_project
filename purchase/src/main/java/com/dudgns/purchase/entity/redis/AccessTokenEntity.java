package com.dudgns.purchase.entity.redis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@Builder
@Getter
@Setter
@RedisHash(value = "accessToken")
public class AccessTokenEntity {
    @Id
    private UUID userGUID;
    private String accessToken;
    @TimeToLive
    private Long expire = 900L;
}
