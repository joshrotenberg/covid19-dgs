CREATE TABLE  county (
    id SERIAL PRIMARY KEY NOT NULL UNIQUE,
    date DATE,
    county VARCHAR (255) NOT NULL,
    state VARCHAR (255) NOT NULL,
    fips VARCHAR (255) NOT NULL,
    cases INTEGER,
    deaths INTEGER
);