insert into devices (name, price)
values ('samsung tv', 45000),
        ('iphone', 80000),
        ('pocophone', 4000),
        ('xbox', 24000),
        ('laptop', 38000);

insert into people (name)
values ('Ivan'),
        ('Sergei'),
        ('Dmitriy'),
        ('Anna'),
        ('Egor');

insert into devices_people (device_id, people_id)
values (1,1),
        (1,3),
        (1,5),
        (2,2),
        (3,3),
        (3,4),
        (4,1),
        (4,5),
        (5,2),
        (5,4);

select avg(d.price)
from devices_people dp
join devices d on dp.device_id = d.id;

select p.name, avg(price)
from devices_people dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name;

select p.name as Name, avg(price) as avgPrice
from devices_people dp
join devices d on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name
having avg(price) > 5000