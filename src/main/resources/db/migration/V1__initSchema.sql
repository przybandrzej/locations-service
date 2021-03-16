CREATE TABLE address
(
    id                bigint NOT NULL,
    line_one          character varying(255),
    line_two          character varying(255),
    city_town_spot_id bigint NOT NULL
);

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE address_id_seq OWNED BY address.id;

CREATE TABLE area_place
(
    id             bigint NOT NULL,
    area           double precision,
    created        timestamp without time zone,
    description    character varying(255),
    modified       timestamp without time zone,
    name           character varying(255),
    paid_admission boolean,
    pets_allowed   boolean,
    unit           character varying(255),
    spot_id        bigint,
    type_id        bigint NOT NULL
);

CREATE SEQUENCE area_place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE area_place_id_seq OWNED BY area_place.id;

CREATE TABLE area_place_type
(
    id       bigint NOT NULL,
    created  timestamp without time zone,
    modified timestamp without time zone,
    name     character varying(255)
);

CREATE SEQUENCE area_place_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE area_place_type_id_seq OWNED BY area_place_type.id;

CREATE TABLE city_town_spot
(
    id        bigint NOT NULL,
    latitude  numeric(19, 2),
    longitude numeric(19, 2),
    name      character varying(255),
    parent_id bigint
);

CREATE SEQUENCE city_town_spot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE city_town_spot_id_seq OWNED BY city_town_spot.id;

CREATE TABLE comment
(
    id                bigint NOT NULL,
    content           character varying(255),
    created           timestamp without time zone,
    area_place_id     bigint,
    food_place_id     bigint,
    point_location_id bigint,
    stay_place_id     bigint
);

CREATE SEQUENCE comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE comment_id_seq OWNED BY comment.id;

CREATE TABLE food_place
(
    id          bigint NOT NULL,
    created     timestamp without time zone,
    description character varying(255),
    food_rate   character varying(255),
    modified    timestamp without time zone,
    name        character varying(255),
    price_rate  character varying(255),
    address_id  bigint,
    type_id     bigint NOT NULL
);

CREATE SEQUENCE food_place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE food_place_id_seq OWNED BY food_place.id;

CREATE TABLE food_place_type
(
    id       bigint NOT NULL,
    created  timestamp without time zone,
    modified timestamp without time zone,
    name     character varying(255)
);

CREATE SEQUENCE food_place_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE food_place_type_id_seq OWNED BY food_place_type.id;

CREATE TABLE image_url
(
    id                bigint NOT NULL,
    applying_order    real,
    url               character varying(255),
    area_place_id     bigint,
    food_place_id     bigint,
    point_location_id bigint,
    stay_place_id     bigint
);

CREATE SEQUENCE image_url_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE image_url_id_seq OWNED BY image_url.id;

CREATE TABLE point_location
(
    id            bigint NOT NULL,
    created       timestamp without time zone,
    description   character varying(255),
    latitude      numeric(19, 2),
    longitude     numeric(19, 2),
    modified      timestamp without time zone,
    name          character varying(255),
    area_place_id bigint,
    type_id       bigint NOT NULL
);

CREATE SEQUENCE point_location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE point_location_id_seq OWNED BY point_location.id;

CREATE TABLE point_location_type
(
    id       bigint NOT NULL,
    created  timestamp without time zone,
    modified timestamp without time zone,
    name     character varying(255)
);

CREATE SEQUENCE point_location_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE point_location_type_id_seq OWNED BY point_location_type.id;

CREATE TABLE stay_place
(
    id              bigint NOT NULL,
    comfort_rate    character varying(255),
    created         timestamp without time zone,
    description     character varying(255),
    has_car_parking boolean,
    location_rate   character varying(255),
    modified        timestamp without time zone,
    name            character varying(255),
    peace_rate      character varying(255),
    pets_allowed    boolean,
    price_rate      character varying(255),
    standard_rate   character varying(255),
    address_id      bigint,
    type_id         bigint NOT NULL
);

CREATE SEQUENCE stay_place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE stay_place_id_seq OWNED BY stay_place.id;

CREATE TABLE stay_place_type
(
    id       bigint NOT NULL,
    created  timestamp without time zone,
    modified timestamp without time zone,
    name     character varying(255)
);

CREATE SEQUENCE stay_place_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE stay_place_type_id_seq OWNED BY stay_place_type.id;

ALTER TABLE ONLY address
    ALTER COLUMN id SET DEFAULT nextval('address_id_seq'::regclass);

ALTER TABLE ONLY area_place
    ALTER COLUMN id SET DEFAULT nextval('area_place_id_seq'::regclass);

ALTER TABLE ONLY area_place_type
    ALTER COLUMN id SET DEFAULT nextval('area_place_type_id_seq'::regclass);

ALTER TABLE ONLY city_town_spot
    ALTER COLUMN id SET DEFAULT nextval('city_town_spot_id_seq'::regclass);

ALTER TABLE ONLY comment
    ALTER COLUMN id SET DEFAULT nextval('comment_id_seq'::regclass);

ALTER TABLE ONLY food_place
    ALTER COLUMN id SET DEFAULT nextval('food_place_id_seq'::regclass);

ALTER TABLE ONLY food_place_type
    ALTER COLUMN id SET DEFAULT nextval('food_place_type_id_seq'::regclass);

ALTER TABLE ONLY image_url
    ALTER COLUMN id SET DEFAULT nextval('image_url_id_seq'::regclass);

ALTER TABLE ONLY point_location
    ALTER COLUMN id SET DEFAULT nextval('point_location_id_seq'::regclass);

ALTER TABLE ONLY point_location_type
    ALTER COLUMN id SET DEFAULT nextval('point_location_type_id_seq'::regclass);

ALTER TABLE ONLY stay_place
    ALTER COLUMN id SET DEFAULT nextval('stay_place_id_seq'::regclass);

ALTER TABLE ONLY stay_place_type
    ALTER COLUMN id SET DEFAULT nextval('stay_place_type_id_seq'::regclass);

SELECT pg_catalog.setval('address_id_seq', 1, false);

SELECT pg_catalog.setval('area_place_id_seq', 1, false);

SELECT pg_catalog.setval('area_place_type_id_seq', 1, false);

SELECT pg_catalog.setval('city_town_spot_id_seq', 1, false);

SELECT pg_catalog.setval('comment_id_seq', 1, false);

SELECT pg_catalog.setval('food_place_id_seq', 1, false);

SELECT pg_catalog.setval('food_place_type_id_seq', 1, false);

SELECT pg_catalog.setval('image_url_id_seq', 1, false);

SELECT pg_catalog.setval('point_location_id_seq', 1, false);

SELECT pg_catalog.setval('point_location_type_id_seq', 1, false);

SELECT pg_catalog.setval('stay_place_id_seq', 1, false);

SELECT pg_catalog.setval('stay_place_type_id_seq', 1, false);

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);

ALTER TABLE ONLY area_place
    ADD CONSTRAINT area_place_pkey PRIMARY KEY (id);

ALTER TABLE ONLY area_place_type
    ADD CONSTRAINT area_place_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY city_town_spot
    ADD CONSTRAINT city_town_spot_pkey PRIMARY KEY (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY food_place
    ADD CONSTRAINT food_place_pkey PRIMARY KEY (id);

ALTER TABLE ONLY food_place_type
    ADD CONSTRAINT food_place_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY image_url
    ADD CONSTRAINT image_url_pkey PRIMARY KEY (id);

ALTER TABLE ONLY point_location
    ADD CONSTRAINT point_location_pkey PRIMARY KEY (id);

ALTER TABLE ONLY point_location_type
    ADD CONSTRAINT point_location_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY stay_place
    ADD CONSTRAINT stay_place_pkey PRIMARY KEY (id);

ALTER TABLE ONLY stay_place_type
    ADD CONSTRAINT stay_place_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY area_place
    ADD CONSTRAINT fk32o5q2q7yk453mrg40vir72h5 FOREIGN KEY (type_id) REFERENCES area_place_type (id);

ALTER TABLE ONLY area_place
    ADD CONSTRAINT fk53fr8of91627kevr79wdts05h FOREIGN KEY (spot_id) REFERENCES city_town_spot (id);

ALTER TABLE ONLY food_place
    ADD CONSTRAINT fk53joclif9awgq8blyvlyhp9p1 FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fk5vgxhbq9vxqo5iv58r3sex7u1 FOREIGN KEY (food_place_id) REFERENCES food_place (id);

ALTER TABLE ONLY image_url
    ADD CONSTRAINT fk71820y2pwtg4e88i6bgsbjpxa FOREIGN KEY (point_location_id) REFERENCES point_location (id);

ALTER TABLE ONLY point_location
    ADD CONSTRAINT fkblxhscp0xf96o9w22snlsfbkx FOREIGN KEY (area_place_id) REFERENCES area_place (id);

ALTER TABLE ONLY stay_place
    ADD CONSTRAINT fkca230nnwk0j4bhcosuc1oyahc FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE ONLY image_url
    ADD CONSTRAINT fkeeu0vlij5g6v231w7qnymt0e FOREIGN KEY (food_place_id) REFERENCES food_place (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fkjrp67888xixcn6b6t64tbj4tg FOREIGN KEY (area_place_id) REFERENCES area_place (id);

ALTER TABLE ONLY address
    ADD CONSTRAINT fkkjpwyfh1506u5ivxeqm3kaq9 FOREIGN KEY (city_town_spot_id) REFERENCES city_town_spot (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fklrjyarqpwlt8hinccfx6m5piv FOREIGN KEY (point_location_id) REFERENCES point_location (id);

ALTER TABLE ONLY image_url
    ADD CONSTRAINT fknnuxfhb8esya13tvh1jvxxl9m FOREIGN KEY (area_place_id) REFERENCES area_place (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fkoqy8dg0ycmei08q37e0dxt5uy FOREIGN KEY (stay_place_id) REFERENCES stay_place (id);

ALTER TABLE ONLY city_town_spot
    ADD CONSTRAINT fkq0j81ggu5mm323om3k8iyo1jq FOREIGN KEY (parent_id) REFERENCES city_town_spot (id);

ALTER TABLE ONLY food_place
    ADD CONSTRAINT fkq2wl3gr358g7uapjjtjpjftbk FOREIGN KEY (type_id) REFERENCES food_place_type (id);

ALTER TABLE ONLY point_location
    ADD CONSTRAINT fkrs3jfud09e2w53rn97ygp5jxi FOREIGN KEY (type_id) REFERENCES point_location_type (id);

ALTER TABLE ONLY image_url
    ADD CONSTRAINT fkss3lln0nrbr8u5v7b9t5w1ycw FOREIGN KEY (stay_place_id) REFERENCES stay_place (id);

ALTER TABLE ONLY stay_place
    ADD CONSTRAINT fktqvangicmymqmqfqdxb245jir FOREIGN KEY (type_id) REFERENCES stay_place_type (id);
