create table if not exists ss_role (id  bigserial not null, description varchar(255), role_name varchar(255), primary key (id));

create table if not exists ss_user (id  bigserial not null, created_at timestamp, updated_at timestamp, created_by int8, updated_by int8, active boolean, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id));

create table if not exists ss_user_roles (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id));
alter table ss_user_roles add constraint FKdsf9lodg18f29s7chafg2xoew foreign key (role_id) references ss_role;
alter table ss_user_roles add constraint FKgm85s5nl4lh78j6yg1jbs3io4 foreign key (user_id) references ss_user;