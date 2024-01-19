package com.dudgns.purchase.entity.purchase;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemEntity is a Querydsl query type for ItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemEntity extends EntityPathBase<ItemEntity> {

    private static final long serialVersionUID = 1363063613L;

    public static final QItemEntity itemEntity = new QItemEntity("itemEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath company = createString("company");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> idx = createNumber("idx", Integer.class);

    public final ComparablePath<java.util.UUID> ItemGUID = createComparable("ItemGUID", java.util.UUID.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final BooleanPath sale = createBoolean("sale");

    public QItemEntity(String variable) {
        super(ItemEntity.class, forVariable(variable));
    }

    public QItemEntity(Path<? extends ItemEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemEntity(PathMetadata metadata) {
        super(ItemEntity.class, metadata);
    }

}

