create table car_bodies (
body_id serial primary key,
name varchar(255)
);

insert into car_bodies (name)
values ('Sedan'),
	('Hatchback'),
	('Crossover'),
	('Jeep'),
	('Short'),
	('lowdrive')
;

create table car_engines (
engine_id serial primary key,
name varchar(255)
);

insert into car_engines (name)
values ('1.0V'),
	('1.6V'),
	('2.2V'),
	('2.8V'),
	('4V')
;


create table car_transmissions (
transmission_id serial primary key,
name varchar(255));

insert into car_transmissions (name)
values ('mechanic'),
	('auto'),
	('robot')
;

create table cars (
car_id serial primary key,
name varchar(255),
body_id int references car_bodies(body_id),
engine_id int references car_engines(engine_id),
transmission_id int references car_transmissions(transmission_id)
);


insert into cars (name, body_id, engine_id, transmission_id)
values ('solaris', 1, 2, 1),
	('solaris', 1, 1, 2),
	('solaris', 2, 2, 2),
	('solaris', 1, null, null),
	('rio', 2, 3, 1),
	('rio', 2, 1, 3),
	('landruiser', 4, 5, 2),
	('landruiser', 4, 5, 3),
	('tahoe', 4, 5, 2),
	('tahoe', 4, 5, 3),
	('creta', 3, 2, 1),
	('creta', 3, 2, 2),
	('creta', 3, 1, 1),
	('kuga', 3, 2, 1),
	('kuga', 3, 3, 3),
	('focus', 1, 2, 3),
	('focus', 2, 2, 1),
	('focus', 1, 3, 2),
	('focus', 2, 3, 2),
	('jazz', 5, 1, 1),
	('jazz', 5, 1, 2),
	('jazz', 5, 1, 3),
	('jazz', 5, 2, 1)
	;

--Вывести список всех машин и все привязанные к ним детали.
select c.car_id as id, c.name, b.name, e.name, t.name
from cars c
left join car_bodies b using (body_id)
left join car_engines e using (engine_id)
left join car_transmissions t using (transmission_id)
;

--Вывести кузовы, которые не используются НИ в одной машине.
select b.name
from car_bodies b
left join cars c using (body_id)
where c.body_id is null
;

--Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select e.name
from car_engines e
left join cars c using (engine_id)
where c.engine_id is null
;

--Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select t.name
from car_transmissions t
left join cars c using (transmission_id)
where c.transmission_id is null
;
