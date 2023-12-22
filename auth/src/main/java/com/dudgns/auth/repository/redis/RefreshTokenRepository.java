package com.dudgns.auth.repository.redis;

import com.dudgns.auth.entity.redis.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, UUID> {
}
