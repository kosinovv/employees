create table if not exists employee
(
    id         integer        not null
        constraint employees_pk
            primary key,
    tab_num    varchar        not null
        constraint employees_tabnum
            unique,
    firstname  varchar        not null,
    secondname varchar,
    lastname   varchar        not null,
    department varchar,
    salary_sum numeric(10, 2) not null
);

comment on table employee is 'Персонал';

comment on column employee.id is 'Идентификатор сотрудника';

comment on column employee.firstname is 'Имя';

comment on column employee.secondname is 'Отчество';

comment on column employee.lastname is 'Фамилия';

comment on column employee.department is 'Подразделение';

comment on column employee.salary_sum is 'Оклад';

alter table employee
    owner to postgres;

CREATE SEQUENCE IF NOT EXISTS public.employee_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999
    CACHE 1;

ALTER SEQUENCE public.employee_seq
    OWNER TO postgres;

COMMENT ON SEQUENCE public.employee_seq
    IS 'Сиквенс для таблицы персонала';

create table if not exists salary_payment
(
    id           integer        not null
        constraint salary_payment_pk
            primary key,
    employee_id  integer        not null
        constraint salary_payment_emp
            references employee
            on delete cascade,
    salary_sum   numeric(10, 2) not null,
    payment_date date           not null
);

comment on table salary_payment is 'Зарплатные платежи';

comment on column salary_payment.id is 'Идентификатор платежа';

comment on column salary_payment.employee_id is 'Идентификатор сотрудника';

comment on column salary_payment.salary_sum is 'Сумма платежа';

alter table salary_payment
    owner to postgres;

CREATE INDEX IF NOT EXISTS salary_payment_date
    ON salary_payment USING btree
        ("payment_date" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE SEQUENCE IF NOT EXISTS public.salary_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

ALTER SEQUENCE public.salary_seq
    OWNER TO postgres;

create index if not exists salary_payment_empid_idx
    on public.salary_payment (employee_id);

alter table public.employee
    alter column salary_sum type float8 using salary_sum::float8;

alter table public.salary_payment
    alter column salary_sum type float8 using salary_sum::float8;