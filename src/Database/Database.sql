CREATE DATABASE IF NOT EXISTS Mediatheque;

USE Mediatheque;

CREATE TABLE IF NOT EXISTS Abonnes (
                                       Numero INT AUTO_INCREMENT PRIMARY KEY,
                                       Nom VARCHAR(100) NOT NULL,
    DateNaissance DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS Documents (
                                         Numero INT AUTO_INCREMENT PRIMARY KEY,
                                         Type ENUM('Livre', 'DVD', 'CD') NOT NULL,
    Titre VARCHAR(100) NOT NULL,
    NombrePages INT,
    EstAdulte BOOLEAN,
    DureeMinutes INT,
    Emprunteur INT,
    Reserveur INT,
    DateReservation DATETIME
    );
