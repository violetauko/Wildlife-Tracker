SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  age  INTEGER,
  health VARCHAR,
  type VARCHAR
);
CREATE TABLE IF NOT EXISTS sightings (
  id int PRIMARY KEY auto_increment,
  animal_id INTEGER,
  location VARCHAR,
  rangerName VARCHAR,
  sitedAt DATE
);