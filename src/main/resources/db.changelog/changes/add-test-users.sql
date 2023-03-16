--liquibase formatted sql
--changeset <daria>:<add-test-users.sql>

INSERT INTO public.users (id, name, age) VALUES (nextval('user_id_seq'), 'Alice', 25);
INSERT INTO public.users (id, name, age) VALUES (nextval('user_id_seq'), 'Bob', 29);
INSERT INTO public.users (id, name, age) VALUES (nextval('user_id_seq'), 'Jack', 21);

-- rollback DELETE FROM public.users WHERE name in ('Alice', 'Bob', 'Jack');
