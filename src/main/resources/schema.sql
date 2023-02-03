use homeManagement;

create table if not exists households (
    id int not null primary key auto_increment,
    name varchar(100) not null
    );

create table if not exists members (
    id int not null primary key auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    household_id int,
    FOREIGN KEY (household_id) REFERENCES households(id)
    );

create table if not exists tasks (
    id int not null primary key auto_increment,
    title varchar(100) not null,
    description varchar(100) not null,
    is_done boolean not null default 0,
    member_id int,
    FOREIGN KEY (member_id) REFERENCES members(id),
    household_id int,
    FOREIGN KEY (household_id) REFERENCES households(id)
    );


