CREATE TABLE IF NOT EXISTS buy_rate
(
    id text COLLATE pg_catalog."default" NOT NULL,
    time_from time without time zone,
    time_to time without time zone,
    power_rate numeric,
    price_rate numeric,
    buy_date date,
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    CONSTRAINT buy_rate_pkey PRIMARY KEY (id)
)
;