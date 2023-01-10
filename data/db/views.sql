create table cities (
    city_id serial primary key,
    name varchar(100)
);

insert into cities (name) values ('Челябинск');
insert into cities (name) values ('Сургут');
insert into cities (name) values ('Екатеринбург');

create table clients (
    client_id serial primary key,
    name varchar(50),
	city_id int references cities(city_id)
);

insert into clients (name, city_id) values ('Иван Иванов', 1);
insert into clients (name, city_id) values ('Петр Петров', 2);
insert into clients (name, city_id) values ('Сидр Сидоров', 3);

create table authors (
    author_id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    book_id serial primary key,
    name varchar(200),
    author_id integer references authors(author_id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    order_id serial primary key,
    book_id integer references books(book_id),
    client_id integer references clients(client_id)
);

insert into orders (book_id, client_id) values (1, 1);
insert into orders (book_id, client_id) values (3, 1);
insert into orders (book_id, client_id) values (5, 2);
insert into orders (book_id, client_id) values (4, 1);
insert into orders (book_id, client_id) values (2, 2);
insert into orders (book_id, client_id) values (2, 1);
insert into orders (book_id, client_id) values (5, 1);

Написать запрос, который выведет наименование книги, автора, клиента и город отправки,
в котором автор начинается с буквы А и город с буквы Ч
select b.name as Книга, a.name as Автор, cl.name as Клиент, c.name as Город_отправки
from orders o
join books b using (book_id)
join authors a using (author_id)
join clients cl using (client_id)
join cities c using (city_id)
where a.name like 'А%' and c.name like 'Ч%'


Создаем Представление
create view temp_table
	as select b.name as Книга, a.name as Автор, cl.name as Клиент, c.name as Город_отправки
		from orders o
		join books b using (book_id)
		join authors a using (author_id)
		join clients cl using (client_id)
		join cities c using (city_id)
		where a.name like 'А%' and c.name like 'Ч%'

Выборка из представления
select * from temp_table