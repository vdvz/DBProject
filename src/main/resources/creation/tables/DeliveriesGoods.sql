CREATE TABLE Deliveries_goods (
id NUMBER(11) PRIMARY KEY,
provider_id REFERENCES Providers(id) NOT NULL,
good_id REFERENCES Goods(id) NOT NULL,
delivery_id REFERENCES Deliveries(id) NOT NULL,
price NUMBER(11) NOT NULL
)