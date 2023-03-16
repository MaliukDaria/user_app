--liquibase formatted sql
--changeset <daria>:<create-user-table.sql>

CREATE SEQUENCE IF NOT EXISTS public.user_id_seq INCREMENT 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial NOT NULL,
    name varchar(256) NOT NULL,
    age int NOT NULL,
    create_time timestamp with time zone NOT NULL,
    update_time timestamp with time zone NOT NULL,
    CONSTRAINT id PRIMARY KEY (id)
);

ALTER TABLE public.users ALTER COLUMN id SET DEFAULT nextval('user_id_seq');
ALTER TABLE public.users ALTER COLUMN create_time SET DEFAULT now() at time zone 'UTC';
ALTER TABLE public.users ALTER COLUMN update_time SET DEFAULT now() at time zone 'UTC';

-- rollback DROP TABLE IF EXISTS public.users;
-- rollback DROP SEQUENCE IF EXISTS public.user_id_seq;
