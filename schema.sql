CREATE TABLE foods (id SERIAL, name VARCHAR(100), comment VARCHAR(100), price int, date DATE);
INSERT INTO foods (id, name, comment, price, date) VALUES (1, 'apple', 'golden', 10, '22-12-2022');
CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), pwd VARCHAR(50), date DATE);
INSERT INTO users (name, email, pwd, date) VALUES ('admin', 'qwerty@gmail.com', 'admin', '12-12-2012');
