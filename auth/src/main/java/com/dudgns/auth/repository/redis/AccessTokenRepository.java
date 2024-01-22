package com.dudgns.auth.repository.redis;

import com.dudgns.auth.entity.redis.AccessTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccessTokenRepository extends CrudRepository<AccessTokenEntity, UUID> {
}
