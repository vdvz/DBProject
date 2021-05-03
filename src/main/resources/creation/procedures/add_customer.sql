create or replace procedure "18208_VIZIR".ADD_CUSTOMER(v_name in VARCHAR,
                              v_age in NUMBER,
                              v_id out NUMBER)
as
begin
    insert into CUSTOMERS(name, age)
    values (v_name, v_age)
    returning id into v_id;
end;