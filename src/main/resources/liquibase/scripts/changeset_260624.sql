create table users
(
    id      integer not null
            constraint users_pk
            primary key,
    username varchar not null
               constraint users_username
               unique,
    password varchar not null,
    roles varchar not null
);

comment on table users is 'Пользователи';

comment on column users.id is 'Идентификатор пользователя';

comment on column users.username is 'Код пользователя';

comment on column users.password is 'Пароль';

comment on column users.roles is 'Роли пользователя через запятую';

CREATE SEQUENCE users_seq;

COMMENT ON SEQUENCE users_seq
    IS 'Сиквенс для таблицы пользователей';