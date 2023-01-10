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

create or replace function add_tax_st()
	returns trigger as
$$
	BEGIN
	update products
	set price = price + price * 0.2
	where id = (select id from inserted);
	return new;
	END;
$$
language 'plpgsql'
;

create trigger tax_trigger_st
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure add_tax_st()
;

create or replace function add_tax_row()
	returns trigger as
$$
	BEGIN
	new.name = new.name;
	new.producer = new.producer;
	new.count = new.count;
	new.price = new.price * 1.2;
	return new;
	END;
$$
language 'plpgsql'
;

create trigger tax_on_row
	before insert
	on products
	for each row
	execute procedure add_tax_row()
;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function price_log()
	returns trigger as
$$
BEGIN
	insert into history_of_price(name, price, date)
	values (new.name, new.price, current_date);
	return new;
END;
$$
language 'plpgsql'
;

create trigger save_price
after insert
on products
for each row
execute procedure price_log();