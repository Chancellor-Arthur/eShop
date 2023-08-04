create table baskets
(
    id         serial primary key,
    user_id    integer not null references users (id) on delete cascade on update cascade,
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

create table files
(
    id         serial primary key,
    path       varchar not null unique,
    name       varchar,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table types_brands
(
    id         serial primary key,
    type_id    integer references types (id) on delete cascade on update cascade,
    brand_id   integer references brands (id) on delete cascade on update cascade,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table devices
(
    id         serial primary key,
    name       varchar not null unique,
    price      integer not null,
    file_id     integer references files (id) on delete set null on update cascade,
    type_id    integer references types (id) on update cascade,
    brand_id   integer references brands (id) on update cascade,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table devices_info
(
    id          serial primary key,
    device_id   integer references devices (id) on delete cascade on update cascade,
    title       varchar not null,
    description text    not null,
    created_at  timestamp with time zone default now(),
    updated_at  timestamp with time zone default now()
);

create table ratings
(
    id         serial primary key,
    user_id    integer references users (id) on delete cascade on update cascade,
    device_id  integer references devices (id) on delete cascade on update cascade,
    rate       integer not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table baskets_devices
(
    id         serial primary key,
    basket_id  integer references baskets (id) on delete cascade on update cascade,
    device_id  integer references devices (id) on delete cascade on update cascade,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);
