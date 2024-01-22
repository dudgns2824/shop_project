package com.dudgns.auth.user.support;

import com.dudgns.auth.entity.QUserEntity;
import com.dudgns.auth.entity.QUserTokenEntity;
import com.dudgns.auth.entity.UserTokenEntity;
import com.dudgns.auth.user.dto.UserTokenDto;
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
/*        if (userGUID == null) {
            return null;
        }

        QUserTokenEntity qUserTokenEntity = QUserTokenEntity.userTokenEntity;

        return queryFactory
                .selectFrom(qUserTokenEntity)
                .where(qUserTokenEntity.id.userGuid.eq(userGUID))
                .fetchFirst();*/
        return null;
    }

    public UserTokenDto getUserTokenDtoByUserGUID(UUID UserGUID) {
/*        if(UserGUID == null) {
            return null;
        }

        QUserTokenEntity qUserTokenEntity = QUserTokenEntity.userTokenEntity;

        return queryFactory
                .select(Projections.bean(UserTokenDto.class,
                        qUserTokenEntity.id.userGuid.as("userGUID"),
                        qUserTokenEntity.id.refreshToken.as("refreshToken")
                ))
                .from(qUserTokenEntity)
                .where(qUserTokenEntity.id.userGuid.eq(UserGUID))
                .fetchFirst();*/
        return null;
    }

}
