
    create table book (
        borrower_id integer,
        id serial not null,
        lender_id integer,
        author varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table role (
        id bigserial not null,
        name varchar(255) not null unique,
        primary key (id)
    );

    create table users (
        id serial not null,
        mail varchar(255) not null unique,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    );

    create table users_roles (
        user_id integer not null,
        role_id bigint not null
    );

    alter table if exists book 
       add constraint FKsggqg8wvglj03kkw115eq1iq2 
       foreign key (borrower_id) 
       references users;

    alter table if exists book 
       add constraint FKpy31kux5khgediw054afaan6e 
       foreign key (lender_id) 
       references users;

    alter table if exists users_roles 
       add constraint FKt4v0rrweyk393bdgt107vdx0x 
       foreign key (role_id) 
       references role;

    alter table if exists users_roles 
       add constraint FK2o0jvgh89lemvvo17cbqvdxaa 
       foreign key (user_id) 
       references users;
