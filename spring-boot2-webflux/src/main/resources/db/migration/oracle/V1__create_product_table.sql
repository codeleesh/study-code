create table product (
    id number(19,0) not null,
    product_amount number(21,3) not null,
    product_count number(10,0),
    product_name varchar2(100 char) not null,
    product_type varchar2(50 char) not null,
    primary key (id)
);