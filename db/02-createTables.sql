CREATE SEQUENCE if not exists account_autoinc START WITH 10000 INCREMENT BY 1 NO CYCLE;

CREATE TABLE IF NOT EXISTS account
(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR(128) NOT NULL,
    password    VARCHAR(128) NOT NULL,
    status      INTEGER      NOT NULL,
    create_date timestamp    NOT NULL
);

CREATE TABLE IF NOT EXISTS account_confirmation
(
    id   INTEGER PRIMARY KEY,
    code VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS role
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS account_roles
(
    account_id INTEGER REFERENCES account (id),
    role_id    INTEGER REFERENCES role (id)
);

create table if not exists country
(
    id   serial primary key,
    name varchar(128)
);

create table if not exists city
(
    id         serial primary key,
    name       varchar(128),
    country_id integer references country (id)
);

create table if not exists hotel
(
    id                   serial primary key,
    name                 varchar(128),
    city_id              integer references city (id),
    stars                smallint,
    beds                 smallint,
    wifi                 boolean,
    card_payment         boolean,
    pool                 boolean,
    adapted_for_disabled boolean
);

create table if not exists tour
(
    id         serial primary key,
    name       varchar(128),
    hotel_id   integer references hotel (id),
    start_city integer references city (id),
    start_date timestamp,
    end_date   timestamp,
    active     boolean
);

create table if not exists public.order
(
    id          serial primary key,
    account_id  integer references account (id),
    price       double precision,
    create_date timestamp,
    paid        boolean,
    update_date timestamp
);

create table if not exists order_tours
(
    order_id integer references public.order (id),
    tour_id  integer references tour (id)
);

create index on account_confirmation (code);
create index on account (email);
create index on public.order (account_id);