package com.dudgns.purchase.repository.redis;

import com.dudgns.purchase.entity.redis.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, UUID> {
}
