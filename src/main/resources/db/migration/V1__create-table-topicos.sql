create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(500) not null unique,
    fecha_de_creacion varchar(20) not null,
    status varchar(50) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)
);