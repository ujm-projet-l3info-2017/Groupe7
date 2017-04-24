CREATE DATABASE IF NOT EXISTS proj_db;
USE proj_db;

CREATE TABLE IF NOT EXISTS Utilisateur
(pseudo		VARCHAR(32) PRIMARY KEY NOT NULL,
 hash		VARCHAR(64) NOT NULL,
 mail		VARCHAR(64) NOT NULL UNIQUE,
 tel		VARCHAR(10)
 );

CREATE TABLE IF NOT EXISTS Itineraire
(id			INT PRIMARY KEY AUTO_INCREMENT,
 adr_depart		VARCHAR(64),
 adr_arrivee		VARCHAR(64),
 id_liste_points	INT,
 type			ENUM('T_CONDUCTEUR', 'T_PASSAGER')
);

CREATE TABLE IF NOT EXISTS Effectuer_itineraire
(pseudo		VARCHAR(32),
 id_itineraire	INT,
 h_depart	TIMESTAMP,
 FOREIGN KEY (pseudo) REFERENCES Utilisateur(pseudo),
 FOREIGN KEY (id_itineraire) REFERENCES Itineraire(id)
);

CREATE TABLE IF NOT EXISTS Liste_points
(id_liste_points	INT,
 num_point		INT,
 point			FLOAT
);
