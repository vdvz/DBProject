CREATE OR REPLACE TRIGGER tr_ai_trade_room before INSERT ON Trade_room FOR each row
BEGIN
  SELECT sq_trade_room.NEXT
  INTO :new.id
  FROM dual;
END;