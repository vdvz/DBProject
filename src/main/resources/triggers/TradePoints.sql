CREATE OR REPLACE TRIGGER tr_ai_trade_points before INSERT ON Trade_points FOR each row
BEGIN
  SELECT sq_trade_points.NEXT
  INTO :new.id
  FROM dual;
END;