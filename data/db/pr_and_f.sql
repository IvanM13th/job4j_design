create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115)
;
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 150)
;
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 1, 1200)
;

create procedure delete_data_by_price(u_price integer)
language 'plpgsql'
as
$$
	BEGIN
	delete from products
	where price > u_price;
	END;
$$
;

create or replace function f_delete_data_by_price(u_price integer)
returns void
language 'plpgsql'
as
$$
	BEGIN
	delete from products
	where price > u_price;
	END;
$$
;