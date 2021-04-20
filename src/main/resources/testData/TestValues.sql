INSERT INTO customers (name, age) VALUES ('Василье Василий', 21)
INSERT INTO customers (name, age) VALUES ('Ардаков Игорь', 27)
INSERT INTO customers (name, age) VALUES ('Донченко Иван', 23)
INSERT INTO customers (name, age) VALUES ('Кулагина Юлия', 22)
INSERT INTO customers (name, age) VALUES ('Бирюков Евгений', 37)
INSERT INTO customers (name, age) VALUES ('Васильев Валера', 18)
INSERT INTO customers (name, age) VALUES ('Дылдин Алексей', 54)
INSERT INTO customers (name, age) VALUES ('Девин Игорь', 56)
INSERT INTO customers (name) VALUES ('Угаров Виктор')
INSERT INTO customers (name, age) VALUES ('Демчук Алексей', 81)
INSERT INTO customers (name, age) VALUES ('Зюлькин Григорий', 98)
INSERT INTO customers (name, age) VALUES ('Гришина Ольга', 21)
INSERT INTO customers (name) VALUES ('Карсева Полина')

INSERT INTO goods (name) VALUES ('хлеб')
INSERT INTO goods (name) VALUES ('компьютер')
INSERT INTO goods (name) VALUES ('сметана')
INSERT INTO goods (name) VALUES ('лук')
INSERT INTO goods (name) VALUES ('удочка')
INSERT INTO goods (name) VALUES ('пакет')
INSERT INTO goods (name) VALUES ('велосипед')
INSERT INTO goods (name) VALUES ('колесо')
INSERT INTO goods (name) VALUES ('сыр')
INSERT INTO goods (name) VALUES ('фарш')
INSERT INTO goods (name) VALUES ('чеснок')


INSERT INTO trade_types (name) VALUES ('гипермаркет')
INSERT INTO trade_types (name) VALUES ('супермаркет')
INSERT INTO trade_types (name) VALUES ('ларек')
INSERT INTO trade_types (name) VALUES ('магазин у дома')
INSERT INTO trade_types (name) VALUES ('универсам')



INSERT INTO trade_points (type, name, point_size, rent_price, communal_payments, number_of_counters)
VALUES (1, 'лента', 100, 10000, 77777, 4)
INSERT INTO trade_points (type, name, point_size, rent_price, communal_payments, number_of_counters)
VALUES (4, 'продукты', 70, 1000, 9872, 4)
INSERT INTO trade_points (type, name, point_size, rent_price, communal_payments, number_of_counters)
VALUES (5, 'горожанка', 260, 6000, 24332, 2)


INSERT INTO providers (name) VALUES ('ооо поставка')
INSERT INTO providers (name) VALUES ('доставим быстро')
INSERT INTO providers (name) VALUES ('тяк')
INSERT INTO providers (name) VALUES ('тяк спб')
INSERT INTO providers (name) VALUES ('быстровкусно')


INSERT INTO trade_room (trade_points_id) VALUES (1)
INSERT INTO trade_room (trade_points_id) VALUES (2)
INSERT INTO trade_room (trade_points_id) VALUES (2)
INSERT INTO trade_room (trade_points_id) VALUES (3)
INSERT INTO trade_room (trade_points_id) VALUES (1)


INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (1, 1 ,'Виталя')
INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (1, 2 ,'Олег')
INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (2, 1 ,'Серафим')
INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (1, 2 ,'Евдокия')
INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (3, 1 ,'Агафья')
INSERT INTO trade_section_point (trade_point, floor, managers_name) VALUES (3, 1 ,'Льюис')

commit