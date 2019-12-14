create table users (
    id int,
    username varchar(100) unique not null,
    password varchar(100) not null,
    primary key (id)
);