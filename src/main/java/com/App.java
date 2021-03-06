package com;

import com.Animal;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

//home page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//show a new animal form
        get("/animal-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

//process animal form
        post("/animal/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            try {
                Animal animal = new Animal(name);
                animal.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter an animal name.");
            }
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//display animals
        get("/animal-detail", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> allAnimals = Animal.all();
            model.put("animals", allAnimals);
            return new ModelAndView(model, "animal-detail.hbs");
        }, new HandlebarsTemplateEngine());

//endangeredAnimals form
        get("/endangered-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

//process form
        post("/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            Integer age = Integer.valueOf(request.queryParams("age"));
            try {
                EndangeredAnimals endangered = new EndangeredAnimals(name, health, age);
                endangered.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter all input fields.");
            }
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//display
        get("/endangered-detail", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<EndangeredAnimals> endangereds = EndangeredAnimals.all();
            model.put("endangereds", endangereds);
            return new ModelAndView(model, "endangered-detail.hbs");
        }, new HandlebarsTemplateEngine());

//sightings form
        get("/sighting-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form
        post("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Integer animal_id = Integer.parseInt(request.queryParams("animal_id"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            System.out.println(rangerName);
            try {
                Sightings testSighting = new Sightings(animal_id, location, rangerName);
                testSighting.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter all input fields.");
            }
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //display
        get("/sighting-detail", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sightings> sightings = Sightings.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sighting-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}