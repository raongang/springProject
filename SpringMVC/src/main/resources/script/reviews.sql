create table reviews (
    id serial primary key,
    text text,
    book_id integer
)

//리뷰 추가
alter table reviews add column rating int not null default 0 ;

