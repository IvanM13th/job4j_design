create table clubs (
id serial primary key,
name varchar(255),
);

create table players (
id serial primary key,
name varchar(255),
club_id int references clubs(id));

create table clubs (
id serial primary key,
name varchar(255));

create table career (
id serial primary key,
player_id references players(id),
positio_id references position(id));
