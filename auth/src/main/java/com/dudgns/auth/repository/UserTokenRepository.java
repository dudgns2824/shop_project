package com.dudgns.auth.repository;

import com.dudgns.auth.entity.UserTokenEntity;
import com.dudgns.auth.entity.id.UserTokenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, UserTokenId> {

    @Query("select ut from UserTokenEntity ut where ut.id.userGUID = ?1")
    Optional<UserTokenEntity> findByUserGuid(UUID userGuid);
}
