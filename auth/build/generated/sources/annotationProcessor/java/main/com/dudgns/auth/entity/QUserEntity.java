package com.dudgns.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1208984603L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final NumberPath<Integer> idx = createNumber("idx", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public final DateTimePath<java.time.LocalDateTime> UserCreatedDate = createDateTime("UserCreatedDate", java.time.LocalDateTime.class);

    public final StringPath UserEmail = createString("UserEmail");

    public final ComparablePath<java.util.UUID> UserGUID = createComparable("UserGUID", java.util.UUID.class);

    public final StringPath UserId = createString("UserId");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

