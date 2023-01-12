create table clothes (
id serial primary key,
name varchar(255),
count int
);

insert into clothes (name, count) values ('jeans', 1);
insert into clothes (name, count) values ('tshirt', 3);
insert into clothes (name, count) values ('jacket', 5);

begin transaction;

insert into clothes (name, count)
values ('socks', 20);

savepoint first_point;

insert into clothes (name, count) values ('underwear', 11);
insert into clothes (name, count) values ('hat', 2);

savepoint second_point;

delete from clothes
where count > 10;

select * from clothes;

rollback to second_point;

select * from clothes;

rollback to first_point;

rollback;