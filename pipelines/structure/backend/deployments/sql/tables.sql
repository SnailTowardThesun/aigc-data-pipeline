drop table if exists tb_users;
create table tb_users (
    id varchar(64) not null,
    username varchar(255) not null,
    nickname varchar(255) not null,
    password varchar(1024) not null,
    email varchar(255) not null,
    created_at datetime not null DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime not null ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

drop table if exists tb_datasets;
create table tb_datasets (
    id varchar(64) not null,
    name varchar(255) not null,
    description varchar(1024) not null,
    structure blob not null,
    created_at datetime not null DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime not null ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);

drop table if exists tb_users_datasets_permissions;
create table tb_users_datasets_permissions (
    id varchar(64) not null,
    user_id varchar(32) not null,
    dataset_id varchar(32) not null,
    permission varchar(255) not null,
    created_at datetime not null DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime not null ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key (id)
);