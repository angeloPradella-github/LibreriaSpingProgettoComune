 create table libro (
        id integer not null auto_increment,
        is_prenotato bit not null,
        pagine integer not null,
        autore varchar(255),
        casa_editrice varchar(255),
        titolo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        is_admin bit not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table libro (
        id integer not null auto_increment,
        is_prenotato bit not null,
        pagine integer not null,
        autore varchar(255),
        casa_editrice varchar(255),
        titolo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        is_admin bit not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table libro (
        id integer not null auto_increment,
        is_prenotato bit not null,
        pagine integer not null,
        autore varchar(255),
        casa_editrice varchar(255),
        titolo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        is_admin bit not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table libro (
        id integer not null auto_increment,
        is_prenotato bit not null,
        pagine integer not null,
        autore varchar(255),
        casa_editrice varchar(255),
        titolo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        is_admin bit not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;
