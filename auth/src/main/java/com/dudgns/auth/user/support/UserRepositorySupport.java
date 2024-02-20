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

    /*유저 토큰 정보*/
    public UserTokenDto getUserTokenDtoByUserGUID(UUID UserGUID) {
        if(UserGUID == null) {
            return null;
        }

        QUserTokenEntity qUserTokenEntity = QUserTokenEntity.userTokenEntity;

        return queryFactory
                .select(Projections.bean(UserTokenDto.class,
                        qUserTokenEntity.id.userGUID.as("userGUID"),
                        qUserTokenEntity.id.refreshToken.as("refreshToken")
                ))
                .from(qUserTokenEntity)
                .where(qUserTokenEntity.id.userGUID.eq(UserGUID))
                .fetchFirst();
    }

}
