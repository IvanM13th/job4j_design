create table departments (
department_id serial primary key,
name varchar(255)
);

create table employees (
employee_id serial primary key,
name varchar(255),
department_id int references departments(department_id)
);

insert into departments (name)
values ('sales'),
		('production'),
		('management'),
		('warehouse'),
		('it');

insert into employees (name, department_id)
values ('Ivan', 1),
		('Sergei', 1),
		('Darya', 1),
		('Dmitriy', 2),
		('Konstantin', 2),
		('Petr', 2),
		('Egor', 3),
		('Anna', 3),
		('Evgeniy', 4),
		('Alex', 4),
		('Leonid', 4);

3. Используя left join найти департаменты, у которых нет работников
select d.name, count(e.department_id)
from departments d
left join employees e using (department_id)
group by d.name
having count(e.department_id) < 1;

4. Используя left и right join написать запросы,
которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select e.name, d.name
from departments d
left join employees e using (department_id)
where e.name is not null

select e.name, d.name
from departments d
right join employees e using (department_id)

5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
Используя cross join составить все возможные разнополые пары
create table teens (
id serial primary key,
name varchar(255),
gender varchar(1)
);

insert into teens (name, gender)
values ('Alex', 'M'),
		('Ivan', 'M'),
		('Katya', 'F'),
		('Tanya', 'F'),
		('Vasiliy', 'M'),
		('Vasilisa', 'F'),
		('Sergei', 'M'),
		('Alina', 'F'),
		('Dmitriy', 'M');

select t1.name as a, t2.name as b
from teens t1
cross join teens t2
where t1.gender <> t2.gender