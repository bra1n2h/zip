-- liquibase formatted sql

-- changeset nmozgowoi:1

ALTER TABLE users
    ADD COLUMN info VARCHAR(128);