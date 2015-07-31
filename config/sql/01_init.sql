-- Support du multilingue
CREATE TABLE IF NOT EXISTS lang(
	id VARCHAR(50),
	df text NOT NULL,
	fr text NOT NULL,
	en text NOT NULL,
	CONSTRAINT pk_lang PRIMARY KEY (id)
);

-- Creation de la table des compagnies
CREATE TABLE IF NOT EXISTS companies(
	company_id VARCHAR(10),
	company_label VARCHAR(30) NOT NULL,
	address VARCHAR(30),
	additionnal_address VARCHAR(30),
	zip_code VARCHAR(5),
	CONSTRAINT pk_companies PRIMARY KEY (company_id)
);

-- Creation de la table des groupes
CREATE TABLE IF NOT EXISTS groups(
	company_id VARCHAR(10),
	group_id VARCHAR(10),
	group_label varchar(30) NOT NULL,
	CONSTRAINT pk_groups PRIMARY KEY (company_id, group_id),
	CONSTRAINT fk_companies FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

-- Creation de la table des utilisateurs
CREATE TABLE IF NOT EXISTS users(
	company_id VARCHAR(10),
	group_id VARCHAR(10),
	login VARCHAR(10),
	password VARCHAR(16) NOT NULL,
	firstname VARCHAR(20) NOT NULL,
	lastname VARCHAR(20) NOT NULL,
	phonenumber VARCHAR(10) NOT NULL,
	email text NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (company_id, group_id, login),
	CONSTRAINT fk_groups FOREIGN KEY (company_id, group_id) REFERENCES groups(company_id, group_id)
);

-- Creation de la table des permissions
CREATE TABLE IF NOT EXISTS permissions(
	company_id VARCHAR(10),
	group_id VARCHAR(10),
	permission_id VARCHAR(50),
	CONSTRAINT pk_permissions PRIMARY KEY (company_id, group_id, permission_id),
	CONSTRAINT fk_groups FOREIGN KEY (company_id, group_id) REFERENCES groups(company_id, group_id)
);