package com.dudgns.backendauth.entity.redis;

import com.dudgns.backendauth.dto.RequestMailDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.List;

@Builder
@Getter
@Setter
@RedisHash(value = "mailRequest")
public class MailRequestEntity {
    @Id
    private String email;
    private List<RequestMailDto> requests;
    private boolean verified;

    @TimeToLive
    private Long expire = 86400L;
}
