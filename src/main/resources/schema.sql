drop table if exists product;

create table product
(
    id bigserial primary key ,
    title varchar(255),
    manufacturer varchar(255),
    description varchar(1023),
    cost bigint,
    status varchar(255) default 'ACTIVE',
    version integer default 0,
    created_by varchar(255),
    created_date timestamp,
    last_modify_by varchar(255),
    last_modify_date timestamp
);