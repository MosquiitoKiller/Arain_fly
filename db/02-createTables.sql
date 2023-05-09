create sequence if not exists account_autoinc start with 10000 increment by 1 no cycle;
create sequence if not exists orders_autoinc start with 10000 increment by 1 no cycle;

create table if not exists account_confirmation
(
    id   integer primary key,
    code varchar(64) not null
);

create table if not exists account
(
    id          serial primary key,
    email       varchar(128) not null,
    password    varchar(128) not null,
    status      integer      not null,
    create_date timestamp    not null,
    constraint account_confirmation_fkey foreign key (id) references account_confirmation
        on delete cascade initially deferred
);



create table if not exists role
(
    id   integer primary key,
    name varchar(32) not null
);

create table if not exists account_roles
(
    account_id integer references account (id) not null,
    role_id    integer references role (id)    not null,
    constraint account_roles_pkey primary key (account_id, role_id)
);

create table if not exists country
(
    id   serial primary key,
    name varchar(128) not null
);

create table if not exists city
(
    id         serial primary key,
    name       varchar(128)                    not null,
    country_id integer references country (id) not null
);

create table if not exists tour
(
    id          serial primary key,
    name        varchar(128)                 not null,
    price       double precision             not null,
    free_places integer                      not null,
    start_city  integer references city (id) not null,
    start_date  timestamp                    not null,
    end_date    timestamp                    not null,
    active      boolean                      not null
);

create table if not exists hotel
(
    id                   serial primary key,
    tour_id              integer references tour (id) not null,
    name                 varchar(128)                 not null,
    city_id              integer references city (id) not null,
    stars                smallint                     not null,
    beds                 smallint                     not null,
    wifi                 boolean                      not null,
    card_payment         boolean                      not null,
    pool                 boolean                      not null,
    adapted_for_disabled boolean                      not null
);

create table if not exists public.orders
(
    id          serial primary key,
    account_id  integer references account (id) not null,
    tour_id     integer references tour (id)    not null,
    count       integer                         not null,
    total_price double precision                not null,
    create_date timestamp                       not null,
    paid        boolean                         not null,
    update_date timestamp
);

create index on account_confirmation (code);
create index on account (email);
create index on public.orders (account_id);