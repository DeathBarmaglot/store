CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), pwd VARCHAR(50), date DATE);
INSERT INTO  users (name, email, pwd, date)
VALUES ('admin', 'qwerty@gmail.com', 'admin', '2020-11-10');

CREATE TABLE foods (id SERIAL PRIMARY KEY, name VARCHAR(100), comment VARCHAR(250), price int, date DATE, email VARCHAR(50));
INSERT INTO foods(id, name, comment, price, date, email)
VALUES (10, 'cheese', 'product', 100, '2022-12-22', 'qwerty@gmail.com');