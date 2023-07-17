create table baskets
(
    id         serial primary key,
    user_id    integer not null references users (id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table types
(
    id         serial primary key,
    name       varchar not null unique,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table brands
(
    id         serial primary key,
    name       varchar not null unique,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table types_brands
(
    id         serial primary key,
    type_id    integer references types (id),
    brand_id   integer references brands (id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table devices
(
    id         serial primary key,
    name       varchar not null unique,
    price      integer not null,
    rating     integer                  default 0,
    image      varchar,
    type_id    integer references types (id),
    brand_id   integer references brands (id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table devices_info
(
    id          serial primary key,
    device_id   integer references devices (id),
    title       varchar not null,
    description text    not null,
    created_at  timestamp with time zone default now(),
    updated_at  timestamp with time zone default now()
);

create table ratings
(
    id         serial primary key,
    user_id    integer references users (id),
    device_id  integer references devices (id),
    rate       integer not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table baskets_devices
(
    id         serial primary key,
    basket_id  integer references baskets (id),
    device_id  integer references devices (id),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);
