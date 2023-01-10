create table clubs (
id serial primary key,
name varchar(255)
);

create table players (
id serial primary key,
name varchar(255)
club_id int references clubs(id)
);

create table clubs (
id serial primary key,
name varchar(255)
);

create table career (
id serial primary key,
player_id int references players(id)
positio_id int references position(id)
);

create table contract (
id serial primary key,
contract_number int
);

create table contracts (
id serial primary key,
name varchar(255),
contract_id int references contract(id) unique
);