package com.dudgns.auth.repository.redis;

import com.dudgns.auth.entity.redis.MailRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailRequestRepository extends CrudRepository<MailRequestEntity, String> {
}
