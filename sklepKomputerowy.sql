--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE ROLE admin;
ALTER ROLE admin WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    phone_number integer NOT NULL,
    email character varying(100) NOT NULL,
    address character varying(200)
);


ALTER TABLE public.clients OWNER TO admin;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clients_id_seq OWNER TO admin;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: delivery; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.delivery (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    price numeric(10,2) NOT NULL
);


ALTER TABLE public.delivery OWNER TO admin;

--
-- Name: delivery_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.delivery_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.delivery_id_seq OWNER TO admin;

--
-- Name: delivery_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.delivery_id_seq OWNED BY public.delivery.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    client_id integer NOT NULL,
    quantity integer NOT NULL,
    delivery_id integer NOT NULL,
    price numeric(10,2) NOT NULL,
    date date NOT NULL,
    status character varying(100) NOT NULL
);


ALTER TABLE public.orders OWNER TO admin;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_id_seq OWNER TO admin;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: orders_items; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.orders_items (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    price numeric(10,2) NOT NULL
);


ALTER TABLE public.orders_items OWNER TO admin;

--
-- Name: orders_items_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.orders_items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_items_id_seq OWNER TO admin;

--
-- Name: orders_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.orders_items_id_seq OWNED BY public.orders_items.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    category character varying(50) NOT NULL,
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.products OWNER TO admin;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO admin;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: services; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.services (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    price numeric(10,2) NOT NULL
);


ALTER TABLE public.services OWNER TO admin;

--
-- Name: services_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.services_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.services_id_seq OWNER TO admin;

--
-- Name: services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.services_id_seq OWNED BY public.services.id;


--
-- Name: workers; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.workers (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    phone_number integer NOT NULL,
    email character varying(100) NOT NULL,
    address character varying(200) NOT NULL,
    salary integer NOT NULL
);


ALTER TABLE public.workers OWNER TO admin;

--
-- Name: workers_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.workers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.workers_id_seq OWNER TO admin;

--
-- Name: workers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.workers_id_seq OWNED BY public.workers.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: delivery id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.delivery ALTER COLUMN id SET DEFAULT nextval('public.delivery_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: orders_items id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders_items ALTER COLUMN id SET DEFAULT nextval('public.orders_items_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: services id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.services ALTER COLUMN id SET DEFAULT nextval('public.services_id_seq'::regclass);


--
-- Name: workers id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workers ALTER COLUMN id SET DEFAULT nextval('public.workers_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (4, 'Mariusz', 'Zając', 123346789, 'mariusz.zajac@example.com', 'Warszawa');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (5, 'Weronika', 'Pawlak', 987154321, 'weronika.pawlak@example.com', 'Kraków');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (6, 'Adam', 'Kwiatkowski', 555123156, 'adam.kwiatkowski@example.com', 'Gdańsk');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (7, 'Natalia', 'Górska', 777882999, 'natalia.gorska@example.com', 'Poznań');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (8, 'Szymon', 'Kowal', 111322333, 'szymon.kowal@example.com', 'Wrocław');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (9, 'Izabela', 'Michalska', 991888777, 'izabela.michalska@example.com', 'Katowice');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (10, 'Krzysztof', 'Włodarczyk', 445255666, 'krzysztof.wlodarczyk@example.com', 'Łódź');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (11, 'Patrycja', 'Adamczyk', 666773888, 'patrycja.adamczyk@example.com', 'Szczecin');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (12, 'Maciej', 'Piotrowski', 222323444, 'maciej.piotrowski@example.com', 'Gdynia');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (13, 'Agata', 'Kamińska', 333444535, 'agata.kaminska@example.com', 'Bydgoszcz');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (3, 'Mariusz', 'Kowal', 455222133, 'mariusz.kowal@example.com', 'Wrocław');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (2, 'Jan', 'Kowalski', 123456781, 'jan.kowalski@example.com', 'Warszawa');
INSERT INTO public.clients (id, name, surname, phone_number, email, address) VALUES (1, 'Andrzej', 'Nowak', 123456789, 'andrzej.nowak@example.com', 'Kielce');


--
-- Data for Name: delivery; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.delivery (id, name, price) VALUES (1, 'Paczkomat InPost', 13.50);
INSERT INTO public.delivery (id, name, price) VALUES (2, 'Kurier DPD', 20.00);
INSERT INTO public.delivery (id, name, price) VALUES (3, 'Odbiór osobisty', 0.00);
INSERT INTO public.delivery (id, name, price) VALUES (4, 'Paczka polecona', 9.00);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.orders (id, client_id, quantity, delivery_id, price, date, status) VALUES (1, 1, 1, 3, 172.00, '2024-01-21', 'Wysłano');
INSERT INTO public.orders (id, client_id, quantity, delivery_id, price, date, status) VALUES (2, 2, 2, 1, 511.50, '2024-01-22', 'Wysłano');


--
-- Data for Name: orders_items; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.orders_items (id, order_id, product_id, quantity, price) VALUES (2, 1, 5, 1, 172.00);
INSERT INTO public.orders_items (id, order_id, product_id, quantity, price) VALUES (3, 2, 4, 2, 498.00);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.products (id, name, category, price, quantity) VALUES (1, 'Intel Core i5-8600k', 'Procesory', 900.00, 14);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (2, 'AMD RX 5700 XT ', 'Karty graficzne', 1800.00, 23);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (3, 'AOC Gaming 27'' 144Hz', 'Monitory', 1100.00, 12);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (4, 'Dualsense Red', 'Pady', 249.00, 7);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (5, 'Razer Deathadder V2', 'Myszki', 172.00, 34);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (6, 'Logitech G920', 'Kierownice', 750.00, 3);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (7, 'Nintendo Switch ', 'Konsole', 1350.00, 7);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (8, 'SilentiumPC Fortis 3', 'Chłodzenia CPU', 139.99, 62);
INSERT INTO public.products (id, name, category, price, quantity) VALUES (9, 'HyperX Fury 8GB 2400Mhz', 'Pamięć RAM', 240.00, 21);


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.services (id, name, price) VALUES (1, 'Instalacja systemu', 50.00);
INSERT INTO public.services (id, name, price) VALUES (2, 'Składanie komputera', 200.00);
INSERT INTO public.services (id, name, price) VALUES (3, 'Wymiana pasty termoprzewodzącej', 55.00);
INSERT INTO public.services (id, name, price) VALUES (4, 'Czyszczenie komputera', 44.00);


--
-- Data for Name: workers; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (1, 'Katarzyna', 'Nowak', 555111233, 'katarzyna.nowak@example.com', 'ul. Kwiatowa 11', 3500);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (2, 'Piotr', 'Wiśniewski', 666223344, 'piotr.wisniewski@example.com', 'ul. Leśna 22', 4000);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (3, 'Anna', 'Kowalczyk', 773334455, 'anna.kowalczyk@example.com', 'ul. Polna 33', 4200);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (4, 'Marek', 'Lis', 888444566, 'marek.lis@example.com', 'ul. Słoneczna 44', 3800);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (5, 'Alicja', 'Dąbrowska', 999556677, 'alicja.dabrowska@example.com', 'ul. Ogrodowa 55', 4500);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (6, 'Tomasz', 'Zieliński', 111667788, 'tomasz.zielinski@example.com', 'ul. Wesoła 66', 4100);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (7, 'Karolina', 'Szymańska', 222778899, 'karolina.szymanska@example.com', 'ul. Bławatną 77', 3800);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (8, 'Michał', 'Jankowski', 333888900, 'michal.jankowski@example.com', 'ul. Lipowa 88', 4200);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (9, 'Jan', 'Kowalski', 444999011, 'jan.kowalski@example.com', 'ul. Brzozowa 99', 3900);
INSERT INTO public.workers (id, name, surname, phone_number, email, address, salary) VALUES (10, 'Ewa', 'Wójcik', 555000112, 'ewa.wojcik@example.com', 'ul. Jesionowa 100', 4000);


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.clients_id_seq', 13, true);


--
-- Name: delivery_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.delivery_id_seq', 4, true);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.orders_id_seq', 2, true);


--
-- Name: orders_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.orders_items_id_seq', 3, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.products_id_seq', 9, true);


--
-- Name: services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.services_id_seq', 4, true);


--
-- Name: workers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.workers_id_seq', 1, false);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: delivery delivery_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.delivery
    ADD CONSTRAINT delivery_pkey PRIMARY KEY (id);


--
-- Name: orders_items orders_items_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders_items
    ADD CONSTRAINT orders_items_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (id);


--
-- Name: workers workers_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT workers_pkey PRIMARY KEY (id);


--
-- Name: orders orders_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- Name: orders orders_delivery_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_delivery_id_fkey FOREIGN KEY (delivery_id) REFERENCES public.delivery(id);


--
-- Name: orders_items orders_items_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders_items
    ADD CONSTRAINT orders_items_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: orders_items orders_items_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.orders_items
    ADD CONSTRAINT orders_items_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- PostgreSQL database dump complete
--
