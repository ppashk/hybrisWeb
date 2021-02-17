drop schema if exists hybris;
create schema hybris;
use hybris;

create table product
(
    id          int not null auto_increment,
    name        varchar(255) unique,
    price       int not null,
    `status`    enum ('OUT_OF_STOCK', 'IN_STOCK', 'RUNNING_LOW'),
    created_at  datetime not null,
    primary key (id)
);

create table orders
(
    id          int not null auto_increment,
    user_id     int not null,
    status      varchar(255) not null,
    created_at  varchar(255) not null,
    primary key (id)
);

create table order_product
(
    id          int not null auto_increment,
    order_id    int not null,
    product_id  int not null,
    primary key (id),
    foreign key (order_id) references orders (id),
    foreign key (product_id) references product (id),
    unique key  `orderProductx` (order_id, product_id)
);