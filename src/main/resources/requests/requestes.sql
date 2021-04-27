-- Получить перечень и общее число поставщиков, поставляющих указанный
-- вид товара, либо некоторый товар в объеме, не менее заданного за
-- весь период сотрудничества, либо за указанный период.
SELECT COUNT(*), P.NAME
FROM DELIVERIES_GOODS DG
         INNER JOIN DELIVERIES D on D.ID = DG.DELIVERY_ID
         INNER JOIN GOODS G on G.ID = DG.GOOD_ID
         INNER JOIN PROVIDERS P on P.ID = D.PROVIDER_ID
WHERE G.NAME='' OR DELIVER_DATE>3;

-- Получить перечень и общее число покупателей, купивших указанный вид
-- товара за некоторый период, либо сделавших покупку товара в объеме,
-- не менее заданного.
SELECT COUNT(*)
FROM SALES S
         INNER JOIN CUSTOMERS C on C.ID = S.CUSTOMER
         INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
         INNER JOIN GOODS G on G.ID = PC.GOOD
WHERE COUNT>=1 OR (PURCHASE_DATE>1 AND PURCHASE_DATE<2);

-- Получить номенклатуру и объем товаров в указанной торговой точке

-- Получить сведения об объеме и ценах на указанный товар по всем
-- торговым точкам, по торговым точкам заданного типа, по конкретной
-- торговой точке.

-- Получить данные о выработке на одного продавца за указанный период
-- по всем торговым точкам, по торговым точкам заданного типа.

-- Получить данные о заработной плате продавцов по всем торговым
-- точкам, по торговым точкам заданного типа, по конкретной торговой точке.

-- Получить сведения о наиболее активных покупателях по всем торговым
-- точкам, по торговым точкам указанного типа, по данной торговой точке.

-- Получить данные о выработке отдельно взятого продавца отдельно
-- взятой торговой точки за указанный период.

-- Получить данные об объеме продаж указанного товара за некоторый
-- период по всем торговым точкам, по торговым точкам заданного типа,
-- по конкретной торговой точке.

-- Получить сведения о покупателях указанного товара за обозначенный,
-- либо за весь период, по всем торговым точкам, по торговым точкам
-- указанного типа, по данной торговой точке.
CREATE OR REPLACE PROCEDURE getInfoAboutCustomers (
    good_name IN varchar(30),
    purchase_date_form IN DATE DEFAULT NULL,
    purchase_date_to IN DATE DEFAULT NULL,
    trade_point_type_id IN NUMBER(11) DEFAULT NULL,
    trade_point_name_id IN NUMBER(11) DEFAULT NULL
)  IS
    QUERY VARCHAR2(1000):= ' ';

        BEGIN

    IF purchase_date_form!=NULL OR purchase_date_to!=NULL THEN
        --throw exception
    END IF;

    IF trade_point_type_id!=NULL THEN
        IF trade_point_name_id!=NULL THEN
            --throw exception
        END IF;
    END IF;

    IF trade_point_name_id!=NULL THEN

    END IF;

    QUERY = 'SELECT C2.NAME, C2.AGE
    FROM SALES S
             INNER JOIN CUSTOMERS C2 on C2.ID = S.CUSTOMER
             INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
             INNER JOIN GOODS G on G.ID = PC.GOOD
             INNER JOIN SELLERS S2 on S2.ID = S.SELLER
             INNER JOIN TRADE_POINTS TP on TP.ID = S2.TRADE_POINT
             INNER JOIN TRADE_TYPES TT on TP.TYPE = TT.ID
    WHERE G.NAME='':1''' + QUERY;

    EXECUTE IMMEDIATE QUERY USING good_name;

END getInfoAboutCustomers;
-- Получить сведения о поставках определенного товара указанным
-- поставщиком за все время поставок, либо за некоторый период.

-- Получить данные об отношении объема продаж к объему торговых
-- площадей, либо к числу торговых залов, либо к числу прилавков по
-- торговым точкам указанного типа, о выработке отдельно взятого
-- продавца торговой точки, по заданной торговой точке.

-- Получить данные о рентабельности торговой точки: соотношение объема
-- продаж к накладным расходам (суммарная заработная плата продавцов +
-- платежи за аренду, коммунальные услуги) за указанный период.

-- Получить сведения о поставках товаров по указанному номеру заказа.

-- Получить данные о товарообороте торговой точки, либо всех торговых
-- определенной группы за указанный период.
