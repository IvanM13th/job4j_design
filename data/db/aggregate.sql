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