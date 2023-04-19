-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le : ven. 31 mars 2023 à 09:33
-- Version du serveur : 10.11.2-MariaDB-1:10.11.2+maria~ubu2204
-- Version de PHP : 8.1.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";





/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `myspotifydata`
--
use `statify`;

-- DROP TABLE IF EXISTS `historics`, `playlist_tracks`, `tracks`, `playlists`, `spotify_user`, `albums`, `artists`, `user`;
-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
   PRIMARY KEY (id)
  ) ENGINE=InnoDB;



-- --------------------------------------------------------

--
-- Structure de la table `spotify_user`
--

CREATE TABLE IF NOT EXISTS `spotify_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `spotify_username` varchar(100) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `creation_time` date DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY spotify_username(spotify_username),
  UNIQUE KEY email(email)
) ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Structure de la table `artists`
--

CREATE TABLE IF NOT EXISTS `artists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- ------------------------------------------------------

--
-- Structure de la table `albums`
--

CREATE TABLE IF NOT EXISTS `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `artist_id` int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (artist_id) REFERENCES artists(id)
) ENGINE=InnoDB;



-- --------------------------------------------------------

--
-- Structure de la table `tracks`
--

CREATE TABLE IF NOT EXISTS `tracks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `localtrack` boolean DEFAULT NULL,
  `uri` varchar(100) DEFAULT NULL,
  `episode` boolean DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (album_id) REFERENCES albums(id)
) ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Structure de la table `playlists`
--

CREATE TABLE IF NOT EXISTS `playlists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `last_modified_at` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `number_of_followers` int(11) DEFAULT NULL,
  `spotify_user_id` int(11) DEFAULT NULL,
  `number_of_tracks` int(11) DEFAULT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (spotify_user_id) REFERENCES spotify_user(id)
) ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Structure de la table `playlist_tracks`
--

CREATE TABLE IF NOT EXISTS `playlist_tracks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playlist_id` int(11) DEFAULT NULL,
  `track_id` int(11) DEFAULT NULL,
  `added_date` date DEFAULT NULL,
  PRIMARY KEY (id),
FOREIGN KEY (playlist_id) REFERENCES playlists(id),
FOREIGN KEY (track_id) REFERENCES tracks(id)
) ENGINE=InnoDB;


-- --------------------------------------------------------

--
-- Structure de la table `historics`
--

CREATE TABLE IF NOT EXISTS `historics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spotify_user_id` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL,
  `track_id` int(11) DEFAULT NULL,
  `msplayed` double DEFAULT NULL,
  `album_id`  int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (spotify_user_id) REFERENCES spotify_user(id),
  FOREIGN KEY (album_id) REFERENCES albums(id),
  FOREIGN KEY (artist_id) REFERENCES artists(id),
  FOREIGN KEY (track_id) REFERENCES tracks(id)
) ENGINE=InnoDB;
