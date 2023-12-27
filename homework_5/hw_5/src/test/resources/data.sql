insert into AUTHORS (id, `name`) values (1, 'Пушкин');
insert into AUTHORS (id, `name`) values (2, 'Лермонтов');
insert into AUTHORS (id, `name`) values (3, 'А. и Б. Стругацкие');
insert into AUTHORS (id, `name`) values (4, 'Толкиен');

insert into GENRES (id, `name`) values (1, 'Роман');
insert into GENRES (id, `name`) values (2, 'Поэма');
insert into GENRES (id, `name`) values (3, 'Фентези');
insert into GENRES (id, `name`) values (4, 'Фантастика');
insert into GENRES (id, `name`) values (5, 'Мемуары');

insert into books(id, title, isbn, genre_id, author_id) values (1, 'Руслан и Людмила', '978-5-9268-2735-1', 2, 1);
insert into books(id, title, isbn, genre_id, author_id) values (2, 'Евгений Онегин', '9780460875950', 1, 1);
insert into books(id, title, isbn, genre_id, author_id) values (3, 'Понедельник начинается в субботу', '978-5-17-090334-4', 3, 3);
insert into books(id, title, isbn, genre_id, author_id) values (4, 'Хищные вещи века', '978-5-17-094720-1', 3, 4);
insert into books(id, title, isbn, genre_id, author_id) values (5, 'Властелин колец', '978-5-17-089322-5', 4, 3);

commit;