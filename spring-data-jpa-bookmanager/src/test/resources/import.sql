-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'martin', 'martin@fastcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'dennis', 'dennis@fastcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sophia', 'sophia@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'james', 'james@slowcampus.com', now(), now());

-- call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'martin', 'martin@another.com', now(), now());

insert into publisher(`id``, `name`) values (1, '패스트캠퍼스');

insert into book(`id`, `name`, `publisher_id`, `deleted`, 'status') values (1, '패키지', 1, false, 100);

insert into book(`id`, `name`, `publisher_id`, `deleted`, 'status') values (2, 'spring package', 1, false, 200);

insert into book(`id`, `name`, `publisher_id`, `deleted`, 'status') values (3, 'spring package2', 1, false, 100);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (1, '내인생을 바꾼 책', '너무너무 좋았어요', 5.0, 1, 1);
insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (2, '내인생을 바꾼 책2', '너무너무 좋았어요2', 3.0, 2, 2);
insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values (3, '내인생을 바꾼 책3', '너무너무 좋았어요3', 3.0, 1, 2);


insert into comment(`id`, `comment`, `review_id`) values (1, '좋아요', 1);
insert into comment(`id`, `comment`, `review_id`) values (2, '좋아요2', 1);
insert into comment(`id`, `comment`, `review_id`) values (3, '좋아요3', 2);