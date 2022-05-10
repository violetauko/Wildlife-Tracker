## Wildlife Tracker

## Author
Violet Auko

## Description
An application that allows Rangers to track wildlife sightings in the area.

## Installation
#### git clone <https://github.com/violetauko/Wildlife-tracker.git> this repository
#### cd Wildlife-tracker

## Creating Database
1.Launch postgres

2.Type in psql

Run these commands

3. CREATE DATABASE wildlife_tracker;

4. \c wildlife_tracker;

5. CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar,health varchar, age varchar);

6. CREATE TABLE wildlife_tracker=# CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, rangerName varchar, timestamp );

7. CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker_test;

## Requirements
The applications allow users to do the following:

1.Add a new animal

2.Add an endangered animal

3.Add an animal Sighting

## Licence
MIT