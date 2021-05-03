CREATE TABLE Trade_section_point (
id NUMBER(11) PRIMARY KEY,
trade_point REFERENCES TRADE_POINTS(id) NOT NULL,
floor NUMBER(11),
managers_name VARCHAR2(30)
)