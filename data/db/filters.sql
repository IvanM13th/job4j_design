create table type (
id serial primary key,
name varchar(50));

create table product (
id serial primary key,
name varchar(50),
type_id int references type(id),
expired_date date,
price decimal(8,2));


insert into type (name)
values ('СЫР'), ('МОЛОКО'), ('КОФЕ'), ('ЧАЙ'), ('КОЛБАСА'), ('КРУПА');

insert into product (name, type_id, expired_date, price)
values ('Гауда', 1, '2023-01-13', 750),
		('Ламбер', 1, '2023-01-29', 900),
		('Село зеленое', 2, '2023-01-10', 70),
		('Простоквашино', 2, '2023-01-14', 140),
		('Lavazza', 3, '2023-07-30', 600),
		('Nescafe', 3, '2023-12-13', 400),
		('Азерчай', 4, '2023-11-11', 80),
		('Lipton', 4, '2024-12-13', 60),
		('Гречка', 5, '2023-12-31', 120),
		('Рис', 5, '2023-08-30', 55);

1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from product p
where p.type_id = (
		select id
		from type
		where name = 'СЫР'
);

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select name
from product
where name like '%мороженое%';

3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select name
from product
where expired_date < current_date;

4. Написать запрос, который выводит самый дорогой продукт.
Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select name
from product
where price in (
	select max(price)
	from product
);


5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
В виде имя_типа, количество
select t.name as имя_типа, count(p.name) as количество
from product p
join type t on p.type_id = t.id
group by t.name;

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select name
from product
where type_id in (
	select id
	from type
	where name in ('СЫР', 'МОЛОКО')
);

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name as ТИП, count(p.name) as Количество
from product p
join type t on p.type_id = t.id
group by t.name
having count(p.name) < 10;

8. Вывести все продукты и их тип.
select p.name as Продукт, t.name as ТИП
from product p
join type t on p.type_id = t.id;





