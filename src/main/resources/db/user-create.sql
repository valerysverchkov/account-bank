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



