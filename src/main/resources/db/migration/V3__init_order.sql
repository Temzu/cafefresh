create table orders
(
    id           bigserial primary key,
    price        numeric(8, 2) not null,
    user_id      bigint references users (id),
    client_name  varchar(30)   not null,
    address      varchar(255)  not null,
    phone        varchar(32)   not null,
    order_status integer   default 1,
    order_type   integer   default 1,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    price             numeric(8, 2) not null,
    price_per_product numeric(8, 2) not null,
    product_id        bigint references products (id),
    order_id          bigint references products (id),
    quantity          int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (price, user_id, address, phone, client_name, order_type, order_status)
values (28, 1, 'Улица Энергетиков 28', '89008008080', 'ivan', 1, 1),
       (1238, 1, 'Улица Энергетиков 28', '89008008080', 'ivan', 2, 2),
       (1212, 1, 'Улица Энергетиков 28', '89008008080', 'ivan', 1, 3),
       (2118, 1, 'Улица Энергетиков 28', '89008008080', 'ivan', 2, 4);

insert into order_items (price, price_per_product, product_id, order_id, quantity)
values (28, 28, 2, 1, 1),
       (28, 28, 3, 2, 3),
       (28, 28, 2, 3, 6),
       (28, 28, 3, 4, 1);