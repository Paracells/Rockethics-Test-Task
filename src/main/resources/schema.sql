drop table if exists cats;
create table if not exists cats
(
    id serial primary key,
    name varchar(255),
    age serial

);
