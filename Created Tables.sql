-- Tabla de Usuarios --
create table USERL(
	US_ID int primary key not null auto_increment unique,
    US_NAME varchar(100) not null unique,
    US_PASSW varchar(255) not null
);
-- Tabla de Autores --
create table AUTHOR(
	AUT_ID int primary key not null auto_increment unique,
    AUT_NAME varchar(100) not null,
    AUT_LNAME varchar(100) 
);
-- Tabla de Categorias --
create table CATEGORY(
	CAT_ID int primary key not null auto_increment unique,
    CAT_NAME varchar(100) not null,
    CAT_DESC text
);
-- Tabla de Libros --
create table BOOK(
	BOOK_ISBN varchar(20) primary key not null unique,
    BOOK_TITLE varchar(100) not null,
    BOOK_PAGES int not null,
    CAT_ID int not null,
    BOOK_DESC text,
    BOOK_IMG_URL text,
    foreign key(CAT_ID) references CATEGORY(CAT_ID)
);
-- Tabla intermedia Libro-Autor --
create table BOOK_AUTHOR(
	AUT_ID INT,
    BOOK_ISBN varchar(20),
    primary key(AUT_ID,BOOK_ISBN),
    foreign key(AUT_ID) references AUTHOR(AUT_ID),
    foreign key(BOOK_ISBN) references BOOK(BOOK_ISBN)
);
-- Tabla de Lecturas --
create table LECTURE(
	LECT_ID int primary key auto_increment not null unique,
    US_ID int,
    BOOK_ISBN varchar(20),
    LECT_RPAGES int default 0,
    LECT_STATUS varchar(20),
    LECT_DATE_START date,
    LECT_DATE_END date,
    foreign key(US_ID) references USERL(US_ID),
    foreign key(BOOK_ISBN) references BOOK(BOOK_ISBN)
);
