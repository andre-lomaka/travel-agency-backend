INSERT INTO continent(name)
VALUES('Africa'),
      ('Asia'),
      ('Europe'),
      ('North America'),
      ('South America'),
      ('Antarctica'),
      ('Australia');

INSERT INTO country(name, continent_id)
VALUES('Estonia', 3),
      ('Finland', 3),
      ('Russia', 3),
      ('Latvia', 3),
      ('Lithuania', 3);

INSERT INTO city(name, country_id)
VALUES('Tallinn', 1),
      ('Helsinki', 2);

INSERT INTO hotel(number_of_stars, name, description, city_id)
VALUES(1, 'Some hotel in Tallinn', '', 1),
      (2, 'Some hotel in Helsinki', '', 2);

INSERT INTO airport(name, city_id)
VALUES('Lennart Meri', 1),
      ('Vantaa', 2);

INSERT INTO board_basis_types(type_string)
VALUES('RO'),
      ('BB'),
      ('HB'),
      ('FB'),
      ('AI');

INSERT INTO trip(from_city_id, from_airport_id, to_city_id, to_hotel_id, to_airport_id, departure_date, return_date, board_basis_type_id, adult_price, child_price, promoted, available, number_of_adult_beds, number_of_child_beds)
VALUES(1, 1, 2, 2, 2, '2020-07-04', '2020-07-05', 1, 3.0, 1.5, false, true, 1, 1),
      (2, 2, 1, 1, 1, '2020-07-05', '2020-07-06', 2, 6.05, 7.0, false, true, 2, 3),
      (1, 1, 2, 2, 2, '2020-07-07', '2020-07-08', 3, 8.0, 9.0, false, true, 1, 0);
