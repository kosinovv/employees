-- Database: employees

-- DROP DATABASE IF EXISTS employees;

CREATE DATABASE employees
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE employees
    IS 'Пересонал и зарплатные платежи';

-- Table: public.employee
-- DROP TABLE IF EXISTS public.employee;

create table employee
(
    id          integer        not null
        constraint employees_pk
            primary key,
    "tabNum"    varchar        not null
        constraint employees_tabnum
            unique,
    firstname   varchar        not null,
    secondname  varchar,
    lastname    varchar        not null,
    department  varchar,
    "salarySum" numeric(10, 2) not null
);

comment on table employee is 'Персонал';

comment on column employee.id is 'Идентификатор сотрудника';

comment on column employee.firstname is 'Имя';

comment on column employee.secondname is 'Отчество';

comment on column employee.lastname is 'Фамилия';

comment on column employee.department is 'Подразделение';

comment on column employee."salarySum" is 'Оклад';

alter table employee
    owner to postgres;

-- SEQUENCE: public.employee_seq

-- DROP SEQUENCE IF EXISTS public.employee_seq;

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


-- Table: public.salary_payment

-- DROP TABLE IF EXISTS public.salary_payment;

create table salary_payment
(
    id            integer        not null
        constraint salary_payment_pk
            primary key,
    "employeeId"  integer        not null
        constraint salary_payment_emp
            references employee
            on delete cascade,
    "salarySum"   numeric(10, 2) not null,
    "paymentDate" date           not null
);

comment on table salary_payment is 'Зарплатные платежи';

comment on column salary_payment.id is 'Идентификатор платежа';

comment on column salary_payment."employeeId" is 'Идентификатор сотрудника';

comment on column salary_payment."salarySum" is 'Сумма платежа';

alter table salary_payment
    owner to postgres;

-- Index: salary_payment_date

-- DROP INDEX IF EXISTS public.salary_payment_date;

CREATE INDEX IF NOT EXISTS salary_payment_date
    ON salary_payment USING btree
    ("paymentDate" ASC NULLS LAST)
    TABLESPACE pg_default;

-- SEQUENCE: public.salary_seq

-- DROP SEQUENCE IF EXISTS public.salary_seq;

CREATE SEQUENCE IF NOT EXISTS public.salary_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

ALTER SEQUENCE public.salary_seq
    OWNER TO postgres;