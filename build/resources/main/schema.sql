CREATE TABLE ORDERS (
    order_id int NOT NULL PRIMARY KEY,
    order_date DATE DEFAULT sysdate NOT NULL,
    company_no INT DEFAULT 0,
    company_name VARCHAR(255),
    item_no INT DEFAULT 0,
    item VARCHAR(255),
    quantity INT DEFAULT 0,
    unit_price INT DEFAULT 0,
    price INT DEFAULT 0
);
