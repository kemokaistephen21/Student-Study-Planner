-- Student Study Planner
-- Database DDL Script
-- Database: 323milestone

CREATE DATABASE IF NOT EXISTS `323milestone`;

USE `323milestone`;

-- Users Table
CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT NOT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Courses Table
CREATE TABLE IF NOT EXISTS `courses` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `userid` BIGINT DEFAULT NULL,
    `code` VARCHAR(255) DEFAULT NULL,
    `instructor` VARCHAR(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Assignments Table
CREATE TABLE IF NOT EXISTS `assignments` (
    `finished` BIT(1) DEFAULT NULL,
    `courseid` BIGINT DEFAULT NULL,
    `duedate` DATETIME(6) DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Tasks Table
CREATE TABLE IF NOT EXISTS `tasks` (
    `courseid` BIGINT DEFAULT NULL,
    `duedate` DATETIME(6) DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Notes Table
CREATE TABLE IF NOT EXISTS `notes` (
    `courseid` BIGINT DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) DEFAULT NULL,
    `content` VARCHAR(255) DEFAULT NULL,
    `title` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);