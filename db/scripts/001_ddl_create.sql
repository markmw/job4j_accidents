CREATE TABLE types (
    id serial PRIMARY key,
    name text
);

create table rules_table (
    id serial primary key,
    name text
);

CREATE TABLE accidents (
  id serial primary key,
  name varchar(2000),
  text text,
  address text,
  accident_type_id int references types(id)
);

CREATE TABLE accident_rule (
  id SERIAL PRIMARY KEY,
  rule_id INT REFERENCES rules_table(id),
  accident_id INT REFERENCES accidents(id)
);