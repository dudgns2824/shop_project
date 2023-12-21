package com.dudgns.backendauth.repository.redis;

import com.dudgns.backendauth.entity.redis.MailRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailRequestRepository extends CrudRepository<MailRequestEntity, String> {
}
