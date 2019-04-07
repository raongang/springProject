create table users ( 
id serial primary key,
email varchar(50) unique,
password varchar(100),
enabled boolean not null
);

insert into users ( email, password, enabled) values
('test@naver.com','1234',true);


select * from users;



alter table users add column sessionkey varchar(50) not null default 'none';
alter table users add column sessionlimit timestamp;
