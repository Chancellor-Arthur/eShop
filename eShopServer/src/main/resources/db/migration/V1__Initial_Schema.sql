create table roles
(
    id   serial primary key,
    name varchar not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table users
(
    id       serial primary key,
    username varchar not null unique,
    password varchar not null,
    email    varchar not null unique,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table users_roles
(
    user_id integer not null,
    role_id integer not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id) on delete cascade on update cascade,
    foreign key (role_id) references roles (id) on delete cascade on update cascade,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users (username, password, email)
values ('admin', '$2y$17$pcD1FIH/S1ExYZttWHEe4.VSoT.oRJ8k8wHiIqMf89ISwgfB8dHjG', 'admin@mail.ru');

insert into users_roles
values (1, 1);