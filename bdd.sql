
CREATE DATABASE district;


CREATE TABLE region(
    id_region int PRIMARY KEY,
    nom_region VARCHAR(90)
);


CREATE TABLE district(
    id_district int PRIMARY KEY,
    nom_district VARCHAR(90),
    id_region int
);


ALTER TABLE district ADD FOREIGN KEY(id_region) REFERENCES region (id_region);