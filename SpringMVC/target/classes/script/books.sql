create table books ( 
	id serial primary key,
	title varchar(50),
	author varchar(50),
	image varchar(255)
);


insert into books ( title, author, image ) 
values ( '테스트','raongang','http://image.yes24.com/goods/24259565/L');

select * from books;