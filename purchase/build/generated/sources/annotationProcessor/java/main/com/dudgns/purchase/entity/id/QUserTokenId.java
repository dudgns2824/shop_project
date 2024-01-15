package com.dudgns.purchase.entity.id;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTokenId is a Querydsl query type for UserTokenId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserTokenId extends BeanPath<UserTokenId> {

    private static final long serialVersionUID = 1454700579L;

    public static final QUserTokenId userTokenId = new QUserTokenId("userTokenId");

    public final StringPath AccessToken = createString("AccessToken");

    public final ComparablePath<java.util.UUID> UserGUID = createComparable("UserGUID", java.util.UUID.class);

    public QUserTokenId(String variable) {
        super(UserTokenId.class, forVariable(variable));
    }

    public QUserTokenId(Path<? extends UserTokenId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTokenId(PathMetadata metadata) {
        super(UserTokenId.class, metadata);
    }

}

