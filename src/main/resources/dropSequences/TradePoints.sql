BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE SQ_Trade_points';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -2289 THEN
      RAISE;
    END IF;
END;