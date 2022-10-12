insert into rules_table(name) values('Статья. 1'),('Статья. 2'),('Статья. 3');

insert into types(name) values('Две машины'),('Машина и человек'),('Машина и велосипед');

insert into accidents(name, text, address, accident_type_id) values('Паша', 'Задел человека', 'ул. Пашкина', 2);
insert into accidents(name, text, address, accident_type_id) values('Олег', 'Задел человека на велосипеде', 'ул. Олега', 3);
insert into accidents(name, text, address, accident_type_id) values('Рома', 'Столкновение двух машин', 'ул. Проспекта типов', 1);

INSERT INTO accident_rule (rule_id, accident_id) VALUES(1, 1);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(2, 2);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(2, 3);