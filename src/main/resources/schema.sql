drop table if exists cart_products cascade;
drop table if exists product;
drop table if exists cart;
drop table if exists user_authorities cascade;
drop table if exists account_user;
drop table if exists authorities;

create table product
(
    id               bigserial primary key,
    title            varchar(255),
    manufacturer     varchar(255),
    description      varchar(1023),
    cost             decimal,
    status           varchar(255) default 'ACTIVE',
    version          integer      default 0,
    created_by       varchar(255),
    created_date     timestamp,
    last_modify_by   varchar(255),
    last_modify_date timestamp
);

create table cart
(
    id               bigserial primary key,
    totalcost        decimal,
    status           varchar(255),
    version          integer default 0,
    created_by       varchar(255),
    created_date     timestamp,
    last_modify_by   varchar(255),
    last_modify_date timestamp
);

create table cart_products
(
    cart_id      bigint,
    products     bigint,
    products_key bigint,
    primary key (cart_id, products_key),
    constraint fk_cart foreign key (cart_id) references cart (id),
    constraint fk_product foreign key (products_key) references product (id)
);

create table account_user
(
    id                      bigserial primary key,
    username                varchar(255) not null,
    password                varchar(255) not null,
    account_non_expired     boolean default true,
    account_non_locked      boolean default true,
    credentials_non_expired boolean default true,
    enabled                 boolean default true
);

create table authorities
(
    id   bigserial primary key,
    role varchar(50) not null
);

create table user_authorities
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint fk_user_roles_user foreign key (user_id) references account_user (id),
    constraint fk_user_roles_role foreign key (role_id) references authorities (id)
);