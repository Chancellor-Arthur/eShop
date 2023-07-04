create table roles
(
    id   serial primary key,
    name varchar not null
);

create table users
(
    id       serial primary key,
    username varchar not null unique,
    password varchar not null,
    email    varchar not null unique
);

create table users_roles
(
    user_id integer not null,
    role_id integer not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users (username, password, email)
values ('admin', '$2y$17$pcD1FIH/S1ExYZttWHEe4.VSoT.oRJ8k8wHiIqMf89ISwgfB8dHjG', 'admin@mail.ru');

insert into users_roles
values (1, 1);