create table clothes (
id serial primary key,
name varchar(255)
);

insert into clothes (name, count) values ('jeans', 1);
insert into clothes (name, count) values ('tshirt', 3);
insert into clothes (name, count) values ('jacket', 5);

select sum(count) from clothes;

update clothes set count = 9 where name = 'jeans';
update clothes set count = 9 where name = 'tshirt';