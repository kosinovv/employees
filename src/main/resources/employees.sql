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

CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_seq'::regclass),
    tabnum character varying COLLATE pg_catalog."default" NOT NULL,
    firstname character varying COLLATE pg_catalog."default" NOT NULL,
    secondname character varying COLLATE pg_catalog."default",
    lastname character varying COLLATE pg_catalog."default" NOT NULL,
    department character varying COLLATE pg_catalog."default",
    salary numeric(10,2) NOT NULL,
    CONSTRAINT employees_pk PRIMARY KEY (id),
    CONSTRAINT employees_tabnum UNIQUE (tabnum)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee
    OWNER to postgres;

COMMENT ON TABLE public.employee
    IS 'Персонал';

COMMENT ON COLUMN public.employee.id
    IS 'Идентификатор сотрудника';

COMMENT ON COLUMN public.employee.firstname
    IS 'Имя';

COMMENT ON COLUMN public.employee.secondname
    IS 'Отчество';

COMMENT ON COLUMN public.employee.lastname
    IS 'Фамилия';

COMMENT ON COLUMN public.employee.department
    IS 'Подразделение';

COMMENT ON COLUMN public.employee.salary
    IS 'Оклад';

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

CREATE TABLE IF NOT EXISTS public.salary_payment
(
    id integer NOT NULL DEFAULT nextval('salary_seq'::regclass),
    "employeeid" integer NOT NULL,
    "paymentdate" date NOT NULL,
    "salarysum" numeric(10,2) NOT NULL,
    CONSTRAINT salary_payment_pk PRIMARY KEY (id),
    CONSTRAINT salary_payment_emp FOREIGN KEY ("employeeid")
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.salary_payment
    OWNER to postgres;

COMMENT ON TABLE public.salary_payment
    IS 'Зарплатные платежи';

COMMENT ON COLUMN public.salary_payment.id
    IS 'Идентификатор платежа';

COMMENT ON COLUMN public.salary_payment."employeeid"
    IS 'Идентификатор сотрудника';

COMMENT ON COLUMN public.salary_payment."paymentdate"
    IS 'Дата платежа';

COMMENT ON COLUMN public.salary_payment."salarysum"
    IS 'Сумма платежа';
-- Index: salary_payment_date

-- DROP INDEX IF EXISTS public.salary_payment_date;

CREATE INDEX IF NOT EXISTS salary_payment_date
    ON public.salary_payment USING btree
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