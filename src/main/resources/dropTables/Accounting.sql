BEGIN EXECUTE IMMEDIATE 'DROP TABLE ACCOUNTING'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;