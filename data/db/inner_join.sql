create table authors (
id serial primary key,
author varchar(255));

create table book (
id serial primary key,
title varchar(255),
author_id int references authors(id));

insert into authors (author)
values ('Джо Аберкромби'), ('Брендон Сандерсон'), ('Борис Акунин'), ('Дмитрий Глуховский');

insert into book (title, author_id)
values ('Кровь и железо', 1), ('Путь королей', 2), ('Левиафан', 3), ('Любовница смерти', 3), ('Сумерки', null);

select b.title, a.author
from book as b
join authors as a on b.author_id = a.id;

select count(b.title) as Количество, a.author as Автор
from book b
join authors a on b.author_id = a.id
group by author
having count(title) > 1;

select title as наименование, author as Автор
from book
join authors on book.author_id = authors.id
where author is not null
