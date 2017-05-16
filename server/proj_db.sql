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
 liste_points VARCHAR(256),
 type			ENUM('T_CONDUCTEUR', 'T_PASSAGER')
);

CREATE TABLE IF NOT EXISTS Effectuer_itineraire
(pseudo		VARCHAR(32),
 id_itineraire	INT,
 h_depart	TIMESTAMP,
 FOREIGN KEY (pseudo) REFERENCES Utilisateur(pseudo),
 FOREIGN KEY (id_itineraire) REFERENCES Itineraire(id)
);

CREATE TABLE IF NOT EXISTS Matchs
(pseudo_conducteur VARCHAR(32),
 pseudo_voyageur VARCHAR(32),
 id_itineraire INT,
 FOREIGN KEY (pseudo_conducteur) REFERENCES Utilisateur(pseudo),
 FOREIGN KEY (pseudo_voyageur) REFERENCES Utilisateur(pseudo),
 FOREIGN KEY (id_itineraire) REFERENCES Itineraire(id)
);