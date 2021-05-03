CREATE TABLE Deliveries (
id NUMBER(11) PRIMARY KEY,
provider_id REFERENCES Providers(id) NOT NULL,
trade_point_id REFERENCES Trade_points(id) NOT NULL,
deliver_date DATE NOT NULL
)