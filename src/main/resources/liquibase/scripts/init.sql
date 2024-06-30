create table employee
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
    salary_sum float8 not null
);

comment on table employee is 'Персонал';

comment on column employee.id is 'Идентификатор сотрудника';

comment on column employee.firstname is 'Имя';

comment on column employee.secondname is 'Отчество';

comment on column employee.lastname is 'Фамилия';

comment on column employee.department is 'Подразделение';

comment on column employee.salary_sum is 'Оклад';

CREATE SEQUENCE public.employee_seq;

COMMENT ON SEQUENCE public.employee_seq
    IS 'Сиквенс для таблицы персонала';

create table salary_payment
(
    id           integer        not null
        constraint salary_payment_pk
            primary key,
    employee_id  integer        not null
        constraint salary_payment_emp
            references employee
            on delete cascade,
    salary_sum   float8 not null,
    payment_date date           not null
);

comment on table salary_payment is 'Зарплатные платежи';

comment on column salary_payment.id is 'Идентификатор платежа';

comment on column salary_payment.employee_id is 'Идентификатор сотрудника';

comment on column salary_payment.salary_sum is 'Сумма платежа';

CREATE INDEX salary_payment_date
    ON salary_payment USING btree
        (payment_date ASC NULLS LAST);

CREATE SEQUENCE salary_seq;

create index on salary_payment (employee_id);