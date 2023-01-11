CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country)
values ('Ivan', 'Ivanov', 30, 'Russia'),
	('Sergei', 'Sergeev', 25, 'Ukraine'),
	('John', 'Smith', 40, 'UK'),
	('Johny', 'Depp', 55, 'USA'),
	('Oliver', 'Smith', 18, 'Australia'),
	('Hugn', 'Folzen', 27, 'Germany'),
	('Vasiliy', 'Vasiliev', 31, 'Russia');

select concat(first_name,' ', last_name) as name
from customers
where age  = (
	select min(age)
	from customers
);

CREATE TABLE orders1 (
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id)
values (3, 1),
		(5, 2),
		(17, 4),
		(66, 2),
		(12, 3),
		(65, 5),
		(22, 6),
		(2, 6),
		(13, 2),
		(22, 1),
		(19, 4);

select concat(first_name, ' ', last_name)
from customers
where id not in (select customer_id from orders1);