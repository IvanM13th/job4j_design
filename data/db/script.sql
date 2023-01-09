create table people (
id serial primary key,
name varchar(255),
city text,
age int);

insert into people (name, city, age)
values 
('Ivan', 'Chelyabinsk', 32),
('Dmitriy', 'Surgut', 28),
('Anna', 'Tyumen', 25);

update people
set age = 52
where name = 'Anna';

delete from people
where name = 'Dmitriy';