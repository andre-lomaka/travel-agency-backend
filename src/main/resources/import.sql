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
      ('Lithuania', 3),
      ('Poland', 3),
      ('USA', 4),
      ('Canada', 4),
      ('Australia', 7);

INSERT INTO city(name, country_id)
VALUES('Tallinn', 1),
      ('Tartu', 1),
      ('Helsinki', 2),
      ('Saint Petersburg', 3),
      ('New York', 7);

INSERT INTO hotel(number_of_stars, name, description, city_id)
VALUES(1, 'Tallinn hotel 1', 'Description 1', 1),
      (2, 'Helsinki hotel 1', 'Description 2', 3),
      (5, 'Tartu hotel 1', 'Description 3', 2),
      (4, 'New York hotel 1', 'Description 4', 5),
      (5, 'New York hotel 2', 'Description 5', 5),
      (4, 'Saint Petersburg hotel 1', 'Description 6', 4);

INSERT INTO airport(name, city_id)
VALUES('Lennart Meri', 1),
      ('Vantaa', 3),
      ('Newark', 5),
      ('JFK', 5),
      ('Pulkovo', 4);

INSERT INTO board_basis_type(type_string)
VALUES('RO'),
      ('BB'),
      ('HB'),
      ('FB'),
      ('AI');

INSERT INTO trip(from_city_id, from_airport_id, to_city_id, to_hotel_id, to_airport_id, departure_date, return_date, board_basis_type_id, adult_price, child_price, promoted, number_of_adult_beds, number_of_child_beds, vacancies)
VALUES(1, 1, 2, 3, 1, '2021-07-20', '2021-07-21', 1, 3.0, 1.5, false, 1, 1, 5),
      (1, 1, 3, 2, 2, '2021-07-21', '2021-07-22', 2, 8.05, 7.0, true, 2, 2, 8),
      (1, 1, 4, 6, 5, '2021-07-27', '2021-08-08', 3, 10.0, 9.0, true, 1, 0, 2),
      (1, 1, 5, 4, 4, '2021-07-22', '2021-07-23', 2, 5.05, 3.2, false, 1, 1, 10),
      (3, 2, 1, 1, 1, '2021-07-23', '2021-07-24', 5, 2.95, 1.5, false, 2, 0, 9),
      (3, 2, 4, 6, 5, '2021-07-24', '2021-07-25', 4, 1.95, 2.0, false, 2, 1, 7);

INSERT INTO purchase(number_of_adults, number_of_children, price, trip_id)
VALUES(1, 0, 2.45, 1),
      (2, 1, 3.95, 3);
