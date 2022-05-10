import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void EndangeredAnimals_instantiatesCorrectly_true() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        assertEquals(true, testAnimal instanceof EndangeredAnimals);

    }

    @Test
    void getName_EndangeredAnimalsInstantiatesWithName_String() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        assertEquals("Lion", testAnimal.getName());
    }

    @Test
    void getAge_EndangeredAnimalsInstantiatesWithAge_int() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        assertEquals(10, testAnimal.getAge());
    }

    @Test
    void getHealth_EndangeredAnimalsInstantiatesWithHealth_String() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        assertEquals("healthy", testAnimal.getHealth());
    }

    @Test
    void equals_returnsTrueIfEndangeredAnimalsNameAreSame() {
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        EndangeredAnimals anotherTestAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        assertTrue(testAnimal.equals(anotherTestAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_true() {
        EndangeredAnimals testAnimal = EndangeredAnimals.all().get(0);
        EndangeredAnimals secondEndangeredAnimal = EndangeredAnimals.all().get(1);
        assertEquals(true, EndangeredAnimals.find(testAnimal.id).equals(testAnimal));
        assertEquals(true, EndangeredAnimals.find(secondEndangeredAnimal.id).equals(secondEndangeredAnimal));
    }
    @Test
    public void find_findEndangeredAnimalById() {
        EndangeredAnimals secondEndangeredAnimal = EndangeredAnimals.all().get(1);
        assertEquals(EndangeredAnimals.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }
    @Test
    public void save_throwsExceptionIfFieldsAreNull(){
        exception.expect (IllegalArgumentException.class);
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        try {
        testAnimal.save();
        } catch (IllegalArgumentException exception){ }
    }
    @Test
    public void deleteByID(){
        EndangeredAnimals testAnimal = new EndangeredAnimals("Lion", "healthy", 10);
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,EndangeredAnimals.find(testAnimal.getId()));

    }

}


