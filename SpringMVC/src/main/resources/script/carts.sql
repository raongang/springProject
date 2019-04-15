create table carts(
 id serial primary key,
 status integer default 0,
 user_id integer references users not null
);

select * from carts;