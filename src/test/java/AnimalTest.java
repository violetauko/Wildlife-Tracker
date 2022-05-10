import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;


import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void animal_instantiatesCorrectly_true() {
        Animal animal = new Animal("Kangaroo");
        assertEquals(true, animal instanceof Animal);

    }

    @Test
    void getName_animalInstantiatesWithName_Kangaroo() {
        Animal animal = new Animal("Kangaroo");
        assertEquals("Kangaroo", animal.getName());
    }

    @Test
    void equals_returnsTrueIfAnimalNameAreSame() {
        Animal animal = new Animal("Kangaroo");
        Animal anotherAnimal = new Animal("Kangaroo");
        assertTrue(animal.equals(anotherAnimal));
    }

    @Test
    void save_insertObjectIntoDatabase_Animal() {
        Animal animal = Animal.all().get(0);
        assertTrue(animal.id==Animal.find(animal.id).id);
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal animal = Animal.all().get(0);
        Animal secondAnimal = Animal.all().get(1);
        assertEquals(true, Animal.find(animal.id).equals(animal));
        assertEquals(true, Animal.find(secondAnimal.id).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal savedAnimal = Animal.all().get(0);
        assertNotEquals(0, savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal secondAnimal = Animal.all().get(0);
        assertEquals(Animal.find(secondAnimal.id), secondAnimal);
    }
    @Test
    public void save_throwsExceptionIfFieldsAreNull(){
        exception.expect (IllegalArgumentException.class);
        Animal animal = new Animal("Kangaroo");
        try {
            animal.save();
        } catch (IllegalArgumentException exception){ }
    }
    @Test
    public void deleteByID(){
        Animal animal = new Animal("Kangaroo");
        animal.save();
        animal.delete();
        assertEquals(null,Animal.find(animal.getId()));

    }
}
