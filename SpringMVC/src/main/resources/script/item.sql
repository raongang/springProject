create table item(
 cart_id integer references carts not null,
 book_id integer references books not null,
 amount integer not null
);

select * from item;