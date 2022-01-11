package com.lovethefeel.springboot.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserIdentity is a Querydsl query type for UserIdentity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserIdentity extends EntityPathBase<UserIdentity> {

    private static final long serialVersionUID = 1057630971L;

    public static final QUserIdentity userIdentity = new QUserIdentity("userIdentity");

    public final NumberPath<java.math.BigDecimal> balanceAmt = createNumber("balanceAmt", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> loanAmt = createNumber("loanAmt", Long.class);

    public final StringPath name = createString("name");

    public final EnumPath<com.lovethefeel.springboot.common.enums.Sexs> sexs = createEnum("sexs", com.lovethefeel.springboot.common.enums.Sexs.class);

    public QUserIdentity(String variable) {
        super(UserIdentity.class, forVariable(variable));
    }

    public QUserIdentity(Path<? extends UserIdentity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserIdentity(PathMetadata metadata) {
        super(UserIdentity.class, metadata);
    }

}

