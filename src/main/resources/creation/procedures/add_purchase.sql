create or replace procedure "18208_VIZIR".add_purchase(v_good in purchase_compositions.good%TYPE,
                              v_count in purchase_compositions.count%TYPE,
                              v_result_price in purchase_compositions.result_price%TYPE,
                              v_purchase_date in purchase_compositions.purchase_date%TYPE,
                              v_id out purchase_compositions.id%TYPE)
as
begin
    insert into PURCHASE_COMPOSITIONS(good, count, result_price, purchase_date)
    values (v_good, v_count, v_result_price, v_purchase_date)
    returning id into v_id;
end;

