import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimals {
    public int id;
//    private int id;
    private String name;
    private String health;
    private int age;
    public static final String HEALTH_HEALTHY="healthy";
    public static final String HEALTH_ILL="ill";
    public static final String HEALTH_OKAY="okay";

    public static final String AGE_NEWBORN="newborn";
    public static final String AGE_YOUNG="young";
    public static final String AGE_ADULT="adult";

    public EndangeredAnimals(String name, String health, int age) {
        this.name = name;
        this.health = health;
        this.age = age;
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
                    this.getHealth() == newEndangeredAnimals.getHealth() &&
                    this.getAge() == newEndangeredAnimals.getAge();
        }

    }
    public int save() {
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO endangeredAnimals (name, health, age) VALUES (:name, :health, :age)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();

        }
        return id;
    }
    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM endangeredAnimals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimals.class);
        }
    }
    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangeredAnimals where id=:id";
            EndangeredAnimals testAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return testAnimal;
        }
    }
}
