import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    Connection con;
    public int id;

    @Override
    protected void before() {
        con = DB.sql2o.open();
    }

    @Override
    protected void after() {
        //try(Connection con = DB.sql2o.open()) {
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
        id = animal.save();
        Animal secondAnimal = new Animal("Bear");
        int id2 = secondAnimal.save();
        Animal animal2 = new Animal("Kangaroo");
        int id3 = animal.save();
    }

    @Test
    void testSave() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        int id = testAnimal.save();
        EndangeredAnimals secondEndangeredAnimals = new EndangeredAnimals("Tiger", "healthy", 12);
        int id1 = secondEndangeredAnimals.save();
    }

    @Test
    void testSave2() {
        Sightings testSighting = new Sightings(1, "Zone A", "Ellah");
        id = testSighting.save();
        Sightings secondTestSighting = new Sightings(2, "Zone B", "Violet");
        id = secondTestSighting.save();

    }
}


