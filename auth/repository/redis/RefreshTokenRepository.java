package com.dudgns.backendauth.repository.redis;

import com.dudgns.backendauth.entity.redis.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, UUID> {
}
