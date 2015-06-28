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
	fr text NOT NULL,
	en text NOT NULL,
	CONSTRAINT pk_lang PRIMARY KEY (id)
);

-- Insert a test user
INSERT INTO users
	SELECT 'test', 'test', 'John', 'Doe', '01-01-1970', 'john.doe@noone.com' FROM users
	WHERE NOT EXISTS (SELECT 1 from users where login = 'test');