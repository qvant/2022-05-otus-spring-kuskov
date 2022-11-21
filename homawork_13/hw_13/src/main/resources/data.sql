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
insert into books(id, title, isbn, genre_id, author_id) values (4, 'Хищные вещи века', '978-5-17-094720-1', 4, 3);
insert into books(id, title, isbn, genre_id, author_id) values (5, 'Властелин колец', '978-5-17-089322-5', 3, 4);

insert into comments(id, book_id, text) values (1, 1, 'Классика из школьной программы');
insert into comments(id, book_id, text) values (2, 5, 'По ней еще фильм сняли');

insert into users (id, name, password, is_locked) values (-1, 'admin', 'admin', false);
insert into users (id, name, password, is_locked) values (-2, 'user', 'user', false);

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 0, 'ROLE_EDITOR'),
(4, 0, 'ROLE_ADMIN')
;

/*INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.library.domain.Author'),
(2, 'ru.otus.spring.library.domain.Genre'),
(3, 'ru.otus.spring.library.domain.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),--Пушкин
(2, 1, 2, NULL, 3, 0),--Лермонтов
(3, 1, 3, NULL, 3, 0),--Стругаццие
(4, 1, 4, NULL, 3, 0);--Толкиен
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(5, 2, 1, NULL, 3, 0),--Роман
(6, 2, 2, NULL, 3, 0),--Поэма
(7, 2, 3, NULL, 3, 0),--Фентези
(8, 2, 4, NULL, 3, 0),--Фантастика
(9, 2, 5, NULL, 3, 0)--Мемуары
;
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(10, 3, 1, NULL, 3, 0),--Руслан и Людмила
(11, 3, 2, NULL, 3, 0),--Евгений Онегин
(12, 3, 3, NULL, 3, 0),--Понедельник начинается в субботу
(13, 3, 4, NULL, 3, 0),--Хищные вещи века
(14, 3, 5, NULL, 3, 0)--Властелин колец
;



INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1),--Пушкин для админа чтение
(1, 2, 2, 1, 1, 1, 1),--Пушкин для пользователя чтение
(2, 1, 1, 1, 1, 1, 1),--Лермотнтов для админа чтение
(3, 1, 1, 1, 1, 1, 1),--Стругацикие для админа чтение
(4, 1, 1, 1, 1, 1, 1)--Толкиен для пользователя чтение
;
INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
(5, 1, 1, 1, 1, 1, 1),--для админа чтение
(6, 1, 1, 1, 1, 1, 1),--для админа чтение
(7, 1, 1, 1, 1, 1, 1),--для админа чтение
(8, 1, 1, 1, 1, 1, 1),--для админа чтение
(9, 1, 1, 1, 1, 1, 1),--для админа чтение
(5, 2, 2, 1, 1, 1, 1),--для админа чтение
(6, 2, 2, 1, 1, 1, 1),--для админа чтение
(7, 2, 2, 1, 1, 1, 1),--для админа чтение
(8, 2, 2, 1, 1, 1, 1),--для админа чтение
(9, 2, 2, 1, 1, 1, 1)--для админа чтение
;
INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
(10, 1, 1, 1, 1, 1, 1),--Руслан и Людмила для админа чтение
(11, 1, 1, 1, 1, 1, 1),-- для админа чтение
(12, 1, 1, 1, 1, 1, 1),-- для админа чтение
(13, 1, 1, 1, 1, 1, 1),-- для админа чтение
(14, 1, 1, 1, 1, 1, 1),-- для админа чтение
(10, 2, 2, 1, 1, 1, 1),--Руслан и Людмила для пользователя чтение
(11, 2, 2, 1, 1, 1, 1)-- для пользователя чтение
;*/

commit;