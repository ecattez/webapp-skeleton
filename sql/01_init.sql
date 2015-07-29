-- Create user table
CREATE TABLE IF NOT EXISTS users(
	login varchar(20),
	password varchar(16) NOT NULL,
	firstname varchar(20) NOT NULL,
	lastname varchar(20) NOT NULL,
	birthday date NOT NULL,
	email text NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (login)
);

-- Create table for multilang support 
CREATE TABLE IF NOT EXISTS lang(
	id varchar(50),
	df text NOT NULL,
	fr text NOT NULL,
	en text NOT NULL,
	CONSTRAINT pk_lang PRIMARY KEY (id)
);