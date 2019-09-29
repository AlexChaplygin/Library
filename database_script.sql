-- Database: library

-- DROP DATABASE library;

CREATE DATABASE library
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    CONNECTION LIMIT = -1;


-- Table: public.reader

-- DROP TABLE public.reader;

CREATE TABLE public.reader
(
    id   SERIAL                 NOT NULL PRIMARY KEY,
    name character varying(255) NOT NULL
)
    WITH (
        OIDS = FALSE
    );
ALTER TABLE public.reader
    OWNER TO postgres;


-- Table: public.book

-- DROP TABLE public.book;

CREATE TABLE public.book
(
    id           SERIAL                 NOT NULL PRIMARY KEY,
    title        character varying(255) NOT NULL,
    author       character varying(255),
    reader_id    int references reader (id),
    release_date date                   NOT NULL
)
    WITH (
        OIDS = FALSE
    );
ALTER TABLE public.book
    OWNER TO postgres;
