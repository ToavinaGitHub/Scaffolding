
CREATE DATABASE district;


CREATE TABLE region(
    id serial PRIMARY KEY,
    nom_region VARCHAR(90)
);


CREATE TABLE district(
    id_district serial PRIMARY KEY,
    nom_district VARCHAR(90),
    id_region int
);


ALTER TABLE district ADD FOREIGN KEY(id_region) REFERENCES region (id);