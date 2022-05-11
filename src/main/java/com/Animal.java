package com;

import org.sql2o.Connection;

import java.util.List;

public class Animal implements DatabaseManagement {
    private String name;

    public int id;
    public String type;
    public static final String DATABASE_TYPE="non-endangeredAnimal";


    public Animal(String name) {
        this.name = name;

        type= DATABASE_TYPE;

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
    @Override
    public void save(){
        if (name.equals("")){
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete(){
        try (Connection con= DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();

        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


}
