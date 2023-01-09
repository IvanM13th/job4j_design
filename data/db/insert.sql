insert into roles (role)
values ('admin'), ('owner'), ('manager');

insert into users (name, role_id)
values ('Ivan', 1), ('Petr', 2), ('Alex', 3);

insert into rules (rule)
values ('Creating'), ('Deleting'), ('Editing');

insert into rolesAndRules (role_id, rule_id)
values (1, 1),(1, 2),(1, 3),(2, 1),(2, 2),(2, 3),(3, 3);

insert into categories (category)
values ('Incorrect number'), ('Broken item');

insert into states (state)
values ('New'), ('In process'), ('Solved');

insert into items (number, category_id, state_id)
values (1, 1, 1), (1, 1, 2), (2, 2, 1);

insert into commentaries (comment, item_id)
values ('We are working on your issues', 1);

insert into attachs (attachm, item_id)
??