create sequence if not exists hibernate_sequence start 1 increment 1;

create table employee
(
    id         int8 not null generated always as identity,
    first_name varchar(255),
    last_name  varchar(255),
    department varchar(2048),
    primary key (id)
);

create table ordr
(
    id              int8         not null generated always as identity,
    status          boolean      not null,
    name            varchar(255),
    type_furniture  varchar(255) not null,
    date_completion timestamp    not null,
    employee_id     int8,
    primary key (id)
);

alter table if exists ordr add constraint employee_order_fk foreign key (employee_id) references employee;

