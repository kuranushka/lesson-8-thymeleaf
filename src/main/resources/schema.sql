drop table if exists cart_products cascade;
drop table if exists product;
drop table if exists cart;

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
    cart_id      bigint primary key,
    products     bigint,
    products_key bigint,
    constraint fk_cart foreign key (cart_id) references cart (id),
    constraint fk_product foreign key (products_key) references product (id)
);

create table usr
(
    id       bigserial primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    enabled  boolean default true
);

create table roles
(
    id   bigserial primary key,
    role varchar(50) not null
);

create table user_roles
(
    user_id bigint,
    role_id bigint,
    primary key (user_id, role_id),
    constraint fk_user_roles_user foreign key (user_id) references usr (id),
    constraint fk_user_roles_role foreign key (role_id) references roles (id)
);