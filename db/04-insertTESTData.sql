insert into country(id, name)
VALUES (1, 'Russia'),
       (2, 'France');
insert into city(id, name, country_id)
VALUES (1, 'Moscow', 1),
       (2, 'Spb', 1);
insert into hotel(id, name, city_id, stars, beds, wifi, card_payment, pool, adapted_for_disabled)
VALUES (1, 'best', 1, 5, 2, true, true, true, true);

insert into tour(id, name, price, free_places, hotel_id, start_city, start_date, end_date, active) VALUES (1,'btour',1,10,1,1,'2022-08-26 12:28:14.621000', '2022-08-26 12:28:14.621000',true);

insert into account_confirmation(id, code) VALUES (9000, 'sdfs');

insert into account(id, email, password, status, create_date)
VALUES (9000, 'test@test.com', '$2a$10$74AAq82ySv4uAq1QVetU7.KOu.ro.sROESP5nMkb8qdtdxFiPo9au', 1,
        '2022-08-26 12:28:14.621000');

insert into account_roles(account_id, role_id)
VALUES (9000, 1),
       (9000, 2);