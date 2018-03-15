# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table employee (
  id                            integer auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  contact_number                varchar(255),
  dob                           varchar(255),
  department                    varchar(255),
  constraint pk_employee primary key (id)
);


# --- !Downs

drop table if exists employee;

