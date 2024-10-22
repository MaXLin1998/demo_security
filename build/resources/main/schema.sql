CREATE TABLE IF NOT EXISTS public.user_info
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_psw character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name_first character varying(64) COLLATE pg_catalog."default" NOT NULL,
    name_last character varying(64) COLLATE pg_catalog."default" NOT NULL,
    user_role character(6) COLLATE pg_catalog."default" DEFAULT 'ADMIN'::bpchar,
    failed_count integer DEFAULT 0,
    create_date timestamp without time zone,
    create_user character varying(255) COLLATE pg_catalog."default",
    update_date timestamp without time zone,
    update_user character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_info_pkey PRIMARY KEY (user_id)
);

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
