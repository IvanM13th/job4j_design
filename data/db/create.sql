create table roles (
id serial primary key,
role text);

create table users (
id serial primary key,
name varchar(255),
role_id int references roles(id));

create table rules (
id serial primary key,
rule text);

create table rolesAndRules(
id serial primary key,
role_id int references roles(id),
rule_id int references rules(id));

create table categories (
id serial primary key,
category varchar(50));

create table states (
id serial primary key,
state varchar(50));

create table item (
id serial primary key,
name varchar(255),
user_id int references users(id));

create table items (
id serial primary key,
number int,
category_id int references categories(id),
state_id int references stets(id));

create table commentaries (
id serial primary key,
comment text,
item_id int references items(id));

create table attachs (
id serial primary key,
description text,
item_id int references items(id));



