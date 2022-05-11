package com;

import com.Animal;
import com.DB;
import com.EndangeredAnimals;
import com.Sightings;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;

public class DatabaseRule extends ExternalResource {
    Connection con;
    public int id;

    @Override
    protected void before() {
        con = DB.sql2o.open();
    }

    @Override
    protected void after() {
        //try(Connection con = com.DB.sql2o.open()) {
        String deleteAnimalsQuery = "DELETE FROM animals *;";
        String deleteEndangeredAnimalsQuery = "DELETE FROM endangeredAnimals *;";
        String deleteSightingsQuery = "DELETE FROM sightings *;";
        con.createQuery(deleteAnimalsQuery).executeUpdate();
        con.createQuery(deleteEndangeredAnimalsQuery).executeUpdate();
        con.createQuery(deleteSightingsQuery).executeUpdate();
        con.close();
        // }
    }

    @Test
    void name() {
        Animal animal = new Animal("Kangaroo");
        animal.save();
        Animal secondAnimal = new Animal("Bear");
        secondAnimal.save();
        Animal animal2 = new Animal("Kangaroo");
        animal.save();
    }

    @Test
    void testSave() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        testAnimal.save();
        EndangeredAnimals secondEndangeredAnimals = new EndangeredAnimals("Tiger", "ill", 12);
        secondEndangeredAnimals.save();
    }

    @Test
    void testSave2() {
        Sightings testSighting = new Sightings(1, "Zone A", "Ellah");
        testSighting.save();
        Sightings secondTestSighting = new Sightings(2, "Zone B", "Violet");
        secondTestSighting.save();

    }
}


