-- we don't know how to generate root <with-no-name> (class Root) :(

create table book
(
    title        varchar(100) not null,
    id           bigint auto_increment
        primary key,
    author       varchar(50)  not null,
    description  text         null,
    publish_date timestamp    not null
);

create table category
(
    name    varchar(30) not null,
    book_id bigint      not null references book(id),
    primary key (name, book_id)
);

create table user_favor_book
(
    user_id varchar(50) not null,
    book_id bigint      not null,
    primary key (user_id, book_id)
);

create table users
(
    username varchar(50)  not null
        primary key,
    password varchar(255) not null,
    email    varchar(100) null
);

