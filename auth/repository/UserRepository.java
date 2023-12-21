package com.dudgns.backendauth.repository;

import com.dudgns.backendauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT count(user) > 0 FROM UserEntity user WHERE user.UserEmail = ?1")
    boolean existsByUserEmail(String email);

    @Query("SELECT user FROM UserEntity user WHERE user.UserId = ?1")
    UserEntity findUserByUserId(String userId);
}
