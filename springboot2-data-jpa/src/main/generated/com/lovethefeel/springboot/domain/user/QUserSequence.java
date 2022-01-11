package com.lovethefeel.springboot.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserSequence is a Querydsl query type for UserSequence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserSequence extends EntityPathBase<UserSequence> {

    private static final long serialVersionUID = -1752026626L;

    public static final QUserSequence userSequence = new QUserSequence("userSequence");

    public final NumberPath<java.math.BigDecimal> balanceAmt = createNumber("balanceAmt", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> loanAmt = createNumber("loanAmt", Long.class);

    public final StringPath name = createString("name");

    public final EnumPath<com.lovethefeel.springboot.common.enums.Sexs> sexs = createEnum("sexs", com.lovethefeel.springboot.common.enums.Sexs.class);

    public QUserSequence(String variable) {
        super(UserSequence.class, forVariable(variable));
    }

    public QUserSequence(Path<? extends UserSequence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserSequence(PathMetadata metadata) {
        super(UserSequence.class, metadata);
    }

}

