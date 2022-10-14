insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$5CpbuJQj2PdXEKSSg.aOguqOGDMivTFcda1oTOR/22QqoN6K.pW.e',
(select id from authorities where authority = 'ROLE_ADMIN'));