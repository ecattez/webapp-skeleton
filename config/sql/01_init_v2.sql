-- Table d'authentification
-- #ano
-- @password_salt : nombre aléatoire pour "salée" le mot de passe utilisateur
-- @password_hash : nombre de hashage pour le mot de passe "salé" de l'utilisateur
CREATE TABLE IF NOT EXISTS authentication(
	ano INTEGER,
	member_name VARCHAR(8),
	password_salt CHAR(24),
	password_hash CHAR(24),
	CONSTRAINT pk_authentication PRIMARY KEY (ano)
);

-- Table des organisations
-- #ono
-- @organisation_name : le nom de l'organisation
-- @organisation address : l'adresse postale de l'organisation
-- @organisation zip_code : le code postal de l'organisation
CREATE TABLE IF NOT EXISTS organisation(
	ono INTEGER,
	organisation_name VARCHAR(20),
	address TEXT,
	zip_code INT(5),
	CONSTRAINT pk_organisation PRIMARY KEY (ono)
);

-- Table des équipes
-- #tno
-- *ono
CREATE TABLE IF NOT EXISTS team(
	tno INTEGER,
	ono INTEGER,
	team_name VARCHAR(10),
	CONSTRAINT pk_team PRIMARY KEY (tno, cno),
	CONSTRAINT fk_organisation FOREIGN KEY (ono) REFERENCES organisation(ono)
);

-- Table des membres
-- #mno
-- *ono
-- @first_name : le prénom du membre
-- @last_name : le nom de famille du membre
-- @email : l'adresse email du membre
-- @phone : le téléphone du membre
CREATE TABLE IF NOT EXISTS member(
	mno INTEGER,
	ono, INTEGER,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	email TEXT,
	phone TEXT,
	CONSTRAINT pk_member PRIMARY KEY (mno, ono),
	CONSTRAINT fk_organisation FOREIGN KEY (ono) REFERENCES organisation(ono)
);

-- Table des permissions
-- #pno
-- @module_name : le nom du module
-- @resource_name : la ressource demandée
-- @recursive_path : oui/non pour autorisé les chemins de type /resource/[something][/[...]]
-- @http_method : la méthode http allouée à cette permission
CREATE TABLE IF NOT EXISTS permission(
	pno INTEGER,
	module_name VARCHAR(10),
	resource_name VARCHAR(10),
	recursive_path BOOLEAN,
	http_method VARCHAR(6),
	CONSTRAINT pk_permission PRIMARY KEY (pno)
);

-- Table des connexions authentifiées
-- #sno : l'identifiant (mno) du membre connecté
-- @token : l'identifiant unique (UUID) d'un membre connecté
-- @expiration_time : la date d'expiration de l'identifiant (un nouvel ID est généré une fois la date dépassée)
CREATE TABLE IF NOT EXISTS securid(
	sno INTEGER,
	token TEXT,
	expiration_time TIMESTAMP,
	CONSTRAINT pk_securid PRIMARY KEY (sno),
	CONSTRAINT fk_member FOREIGN KEY (sno) REFERENCES member(mno)
);