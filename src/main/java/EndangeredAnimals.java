import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimals implements DatabaseManagement {
    public int id;
    private String name;
    private String health;
    private int age;
    public String type;
    public static final String DATABASE_TYPE="endangeredAnimal";

    public static final String HEALTH_HEALTHY="healthy";
    public static final String HEALTH_ILL="ill";
    public static final String HEALTH_OKAY="okay";

    public static final String AGE_NEWBORN="newborn";
    public static final String AGE_YOUNG="young";
    public static final String AGE_ADULT="adult";

    public EndangeredAnimals (String name, String health, int age) {
        this.name = name;
        this.health = health;
        this.age = age;
        type=DATABASE_TYPE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object anotherTestAnimal) {
        if (!(anotherTestAnimal instanceof EndangeredAnimals)) {
            return false;
        } else {
            EndangeredAnimals newEndangeredAnimals = (EndangeredAnimals) anotherTestAnimal;
            return this.getName().equals(newEndangeredAnimals.getName()) &&
                    this.getHealth().equals(newEndangeredAnimals.getHealth()) &&
                    this.getAge() == newEndangeredAnimals.getAge();
        }

    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()){
            if (name.equals("") || health.equals("") || Objects.equals(age, null)){
                throw new IllegalArgumentException("Please enter all input fields.");
            }
            String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();

        }
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type='endangeredAnimal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimals testAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return testAnimal;
        }
    }
    @Override
    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();

        }
    }
}
