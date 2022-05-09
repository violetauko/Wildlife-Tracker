import org.sql2o.Connection;

import java.util.List;

public class Animal {
    public String name;
    public int id;

    public Animal(String name) {
        if (name.equals("")){
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object anotherAnimal){
        if (!(anotherAnimal instanceof Animal)) {
            return false;
        }else {
            Animal newAnimal = (Animal) anotherAnimal;
            return this.getName().equals(newAnimal.getName());

        }
    }

    public int save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            return (int) con.createQuery(sql)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


}
