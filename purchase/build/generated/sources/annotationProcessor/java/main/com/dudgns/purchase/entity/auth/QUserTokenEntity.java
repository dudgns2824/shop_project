package com.dudgns.purchase.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTokenEntity is a Querydsl query type for UserTokenEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTokenEntity extends EntityPathBase<UserTokenEntity> {

    private static final long serialVersionUID = 670739985L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTokenEntity userTokenEntity = new QUserTokenEntity("userTokenEntity");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> expireDate = createDateTime("expireDate", java.time.LocalDateTime.class);

    public final com.dudgns.purchase.entity.id.QUserTokenId id;

    public QUserTokenEntity(String variable) {
        this(UserTokenEntity.class, forVariable(variable), INITS);
    }

    public QUserTokenEntity(Path<? extends UserTokenEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTokenEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTokenEntity(PathMetadata metadata, PathInits inits) {
        this(UserTokenEntity.class, metadata, inits);
    }

    public QUserTokenEntity(Class<? extends UserTokenEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new com.dudgns.purchase.entity.id.QUserTokenId(forProperty("id")) : null;
    }

}

