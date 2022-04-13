create schema ad;

create sequence ad.address_id_seq;
create table ad.address (
    id integer unique not null default nextval('ad.address_id_seq'),
    country varchar,
    city varchar,
    street varchar,
    home varchar,
    PRIMARY KEY (id)
);

create sequence ad.users_id_seq;
create table ad.users (
                          id integer unique not null default nextval('ad.users_id_seq'),
                          name varchar not null,
                          email varchar,
                          address_id integer,
                          PRIMARY KEY (id),
                          FOREIGN KEY (address_id) REFERENCES ad.address(id)
);


create schema dict;
create sequence dict.currency_id_seq;
create table dict.currency(
                                            id integer unique not null default nextval('dict.currency_id_seq'),
                                            name varchar not null,
                                            PRIMARY KEY (id)
);

insert into dict.currency (name)
values ('USD'), ('EUR'), ('RUB'), ('GBP');

create schema bank;
create sequence bank.bank_book_id_seq;
create table bank.bank_book (
                                              id integer unique not null default nextval('bank.bank_book_id_seq'),
                                              user_id integer not null,
                                              number varchar not null ,
                                              amount numeric not null ,
                                              currency integer not null ,
                                              PRIMARY KEY (id),
                                              FOREIGN KEY (user_id) REFERENCES ad.users(id),
                                              FOREIGN KEY (currency) REFERENCES dict.currency(id)
);

create sequence ad.group_id_seq;
create table ad.group (
                          id integer unique not null default nextval('ad.group_id_seq'),
                          name varchar not null,
                          PRIMARY KEY (id)
);

create table ad.users_groups (
    user_id integer not null,
    group_id integer not null,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES ad.users(id),
    FOREIGN KEY (group_id) REFERENCES ad.group(id)
);
