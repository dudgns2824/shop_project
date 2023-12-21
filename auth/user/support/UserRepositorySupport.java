package com.dudgns.backendauth.user.support;

import com.dudgns.backendauth.entity.QUserEntity;
import com.dudgns.backendauth.entity.QUserTokenEntity;
import com.dudgns.backendauth.entity.UserTokenEntity;
import com.dudgns.backendauth.user.dto.UserTokenDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UserTokenEntity getTokenEntity(UUID userGUID) {
        if (userGUID == null) {
            return null;
        }

        QUserTokenEntity qUserTokenEntity = QUserTokenEntity.userTokenEntity;

        return queryFactory
                .selectFrom(qUserTokenEntity)
                .where(qUserTokenEntity.id.UserGUID.eq(userGUID))
                .fetchFirst();
    }

    public UserTokenDto getUserTokenDtoByUserGUID(UUID UserGUID) {
        if(UserGUID == null) {
            return null;
        }

        QUserTokenEntity qUserTokenEntity = QUserTokenEntity.userTokenEntity;

        return queryFactory
                .select(Projections.bean(UserTokenDto.class,
                        qUserTokenEntity.id.UserGUID.as("userGUID"),
                        qUserTokenEntity.id.AccessToken.as("accessToken")
                ))
                .from(qUserTokenEntity)
                .where(qUserTokenEntity.id.UserGUID.eq(UserGUID))
                .fetchFirst();
    }

}
