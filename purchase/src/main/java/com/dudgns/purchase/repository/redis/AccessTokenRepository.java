package com.dudgns.purchase.repository.redis;

import com.dudgns.purchase.entity.redis.AccessTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccessTokenRepository extends CrudRepository<AccessTokenEntity, UUID> {
}
