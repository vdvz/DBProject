CREATE TABLE Deliveries_goods (
id NUMBER(11) PRIMARY KEY,
good_id REFERENCES Goods(id) NOT NULL,
delivery_id REFERENCES Deliveries(id) NOT NULL,
count NUMBER(11) NOT NULL,
price NUMBER(11) NOT NULL
)