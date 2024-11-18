#!/bin/bash
set -e

sleep 30

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

CREATE SCHEMA library 
AUTHORIZATION postgres;

CREATE TABLE library.books
(
    book_id SERIAL,
    title text COLLATE pg_catalog."default" NOT NULL,
    author text COLLATE pg_catalog."default" NOT NULL,
    isbn text COLLATE pg_catalog."default" NOT NULL,
    year integer NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (book_id)
);

CREATE TABLE library.users
(
    user_id SERIAL,
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

CREATE TABLE library.orders
(
    order_id SERIAL,
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT orders_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES library.books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES library.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

insert into "library".users(first_name, last_name) values ('Gaetano','Piazzolla');

insert into "library".books (title,author,isbn,"year") values('MediumPubblication','Me',11111111,2021);

EOSQL