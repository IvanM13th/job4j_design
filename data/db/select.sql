insert into fauna (name, avg_age, discovery_date)
values ('dog', 4500, '0010-01-01'),
('cat', 3800, '0050-01-01'),
('fish', 100, '0005-01-01'),
('pig', 7500, '0150-01-01'),
('bird', 98, '0001-01-01'),
('worm', 5, '2019-01-01'),
('firefly', 2, '0178-01-01'),
('dinosaur', 16548, '0001-01-01')
('omegafishZ', 150, '2020-01-01'),
('fishblue', 187, '1485-01-01');

select name, avg_age, discovery_date
from fauna
where name like '%fish%';

select name, avg_age, discovery_date
from fauna
where avg_age between 10000 and 21000;

select name, avg_age, discovery_date
from fauna
where discovery_date is null;

select name, avg_age, discovery_date
from fauna
where discovery_date < '1950-01-01'