CREATE DATABASE IF NOT EXISTS springmvc;

create table if not exists users
(
    id        bigint auto_increment
        primary key,
    email     varchar(255) not null,
    name      varchar(20)  not null,
    password  varchar(255) null,
    last_name varchar(255) null,
    constraint UK_3g1j96g94xpk3lpxl2qbl985x
        unique (name),
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

create table if not exists roles
(
    id   bigint auto_increment
        primary key,
    role varchar(255) null
);

create table if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id) references roles (id)
);