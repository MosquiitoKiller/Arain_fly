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