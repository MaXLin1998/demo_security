CREATE TABLE IF NOT EXISTS public.ORDERS (
     order_id bigint NOT NULL,
     order_date timestamp NOT NULL,
     company_no bigint DEFAULT 0,
     company_name VARCHAR(255),
    item_no bigint DEFAULT 0,
    item VARCHAR(255),
    quantity bigint DEFAULT 0,
    unit_price bigint DEFAULT 0,
    price bigint DEFAULT 0
);
