-- Получить перечень и общее число поставщиков, поставляющих указанный
-- вид товара, либо некоторый товар в объеме, не менее заданного за
-- весь период сотрудничества, либо за указанный период./*DONE*/
SELECT *
FROM PROVIDERS P
        INNER JOIN DELIVERIES_GOODS DG on P.ID = DG.PROVIDER_ID
        INNER JOIN DELIVERIES D on D.ID = DG.DELIVERY_ID
        INNER JOIN GOODS G on G.ID = DG.GOOD_ID
WHERE G.NAME='' OR DELIVER_DATE>3;

-- Получить перечень и общее число покупателей, купивших указанный вид
-- товара за некоторый период, либо сделавших покупку товара в объеме,
-- не менее заданного./*DONE*/
SELECT C.ID, C.NAME, C.AGE
FROM SALES S
         INNER JOIN CUSTOMERS C on C.ID = S.CUSTOMER
         INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
         INNER JOIN GOODS G on G.ID = PC.GOOD
WHERE COUNT>=1 OR (PURCHASE_DATE>1 AND PURCHASE_DATE<2);

-- Получить номенклатуру и объем товаров в указанной торговой точке

-- Получить сведения об объеме и ценах на указанный товар по всем
-- торговым точкам, по торговым точкам заданного типа, по конкретной
-- торговой точке.
SELECT * FROM ACCOUNTING A
INNER JOIN GOODS G on G.ID = A.GOOD
INNER JOIN TRADE_POINTS TP on TP.ID = A.TRADE_POINT
INNER JOIN TRADE_TYPES TT on TT.ID = TP.TYPE
WHERE 1=1


-- Получить данные о выработке на одного продавца за указанный период
-- по всем торговым точкам, по торговым точкам заданного типа./PROCESS/
SELECT * FROM SALES S
INNER JOIN SELLERS S2 on S2.ID = S.SELLER
INNER JOIN TRADE_POINTS TP on TP.ID = S2.TRADE_POINT
INNER JOIN TRADE_TYPES TT on TT.ID = TP.TYPE
WHERE 1=1



-- Получить данные о заработной плате продавцов по всем торговым
-- точкам, по торговым точкам заданного типа, по конкретной торговой точке./DONE/
SELECT S.ID, S.NAME, S.SALARY, S.TRADE_POINT, S.TRADE_ROOM
    FROM TRADE_POINTS TP
             INNER JOIN SELLERS S ON S.TRADE_POINT=TP.ID
             INNER JOIN TRADE_TYPES TT on TP.TYPE = TT.ID
WHERE 1=1

-- Получить сведения о наиболее активных покупателях по всем торговым
-- точкам, по торговым точкам указанного типа, по данной торговой точке.
SELECT C2.ID as id, C2.NAME as name, C2.AGE as age, COUNT(id) as count
FROM SALES S
        INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
        INNER JOIN CUSTOMERS C2 on C2.ID = S.CUSTOMER
        INNER JOIN SELLERS S2 on S2.ID = S.SELLER
        INNER JOIN TRADE_POINTS TP on TP.ID = S2.TRADE_POINT
WHERE 1=1 /*TODO*/
GROUP BY C2.ID
ORDER BY COUNT(ID) DESC

-- Получить данные об объеме продаж указанного товара за некоторый
-- период по всем торговым точкам, по торговым точкам заданного типа,
-- по конкретной торговой точке.

-- Получить данные о выработке отдельно взятого продавца отдельно
-- взятой торговой точки за указанный период.

-- Получить сведения о покупателях указанного товара за обозначенный,
-- либо за весь период, по всем торговым точкам, по торговым точкам
-- указанного типа, по данной торговой точке./DONE/
 SELECT C2.NAME, C2.AGE
    FROM SALES S
             INNER JOIN CUSTOMERS C2 on C2.ID = S.CUSTOMER
             INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
             INNER JOIN GOODS G on G.ID = PC.GOOD
             INNER JOIN SELLERS S2 on S2.ID = S.SELLER
             INNER JOIN TRADE_POINTS TP on TP.ID = S2.TRADE_POINT
             INNER JOIN TRADE_TYPES TT on TP.TYPE = TT.ID
    WHERE G.NAME

-- Получить сведения о поставках определенного товара указанным
-- поставщиком за все время поставок, либо за некоторый период./*ALMOST DONE - MAKE QUERY*/
SELECT * FROM DELIVERIES_GOODS
INNER JOIN DELIVERIES D on D.ID = DELIVERIES_GOODS.DELIVERY_ID
INNER JOIN PROVIDERS P on P.ID = D.PROVIDER_ID
INNER JOIN GOODS G on G.ID = DELIVERIES_GOODS.GOOD_ID
WHERE G.NAME=''

-- Получить данные об отношении объема продаж к объему торговых
-- площадей, либо к числу торговых залов, либо к числу прилавков по
-- торговым точкам указанного типа, о выработке отдельно взятого
-- продавца торговой точки, по заданной торговой точке.

-- Получить данные о рентабельности торговой точки: соотношение объема
-- продаж к накладным расходам (суммарная заработная плата продавцов +
-- платежи за аренду, коммунальные услуги) за указанный период.

-- Получить сведения о поставках товаров по указанному номеру заказа.
SELECT G.NAME, TP.NAME, P.NAME, D.DELIVER_DATE FROM DELIVERIES_GOODS DG
INNER JOIN PROVIDERS P on P.ID = DG.PROVIDER_ID
INNER JOIN DELIVERIES D on D.ID = DG.DELIVERY_ID
INNER JOIN GOODS G on G.ID = DG.GOOD_ID
INNER JOIN TRADE_POINTS TP on G.NAME = TP.NAME
WHERE D.ID =

-- Получить данные о товарообороте торговой точки, либо всех торговых
-- определенной группы за указанный период.
SELECT * FROM SALES S
INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID = S.PURCHASE_COMPOSITION
INNER JOIN SELLERS S2 on S2.ID = S.SELLER
INNER JOIN TRADE_POINTS TP on TP.ID = S2.TRADE_POINT
INNER JOIN TRADE_TYPES TT on TT.ID = TP.TYPE
WHERE