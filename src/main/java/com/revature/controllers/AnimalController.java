package com.revature.controllers;

import com.revature.DAOs.AnimalDAO;
import com.revature.models.Animal;
import com.revature.models.Habitat;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class AnimalController {
    AnimalDAO aDAO = new AnimalDAO();

    public Handler getAnimalsHandler = ctx -> {
        ArrayList<Animal> animals = aDAO.getAllAnimals();
        ctx.json(animals);
        ctx.status(200);
    };

    /*
    public Handler getAnimalsByIdHandler
    * */

    public Handler getAnimalsByIdHandler = ctx -> {
        int habitat_id = Integer.parseInt(ctx.pathParam("id"));
        ArrayList<Animal> animals = aDAO.getAnimalsByHabitatId(habitat_id);
        if (habitat_id <= 0) {
            ctx.result("Habitat ID must be greater than 0");
            ctx.status(400);
        } else if (animals.isEmpty()) {
            ctx.result("Animals at Habitat ID " + habitat_id + " not found");
            ctx.status(404);
        } else {
            ctx.json(animals);
            ctx.status(200); //200 ok
        }
    };

    public Handler insertAnimalHandler = ctx -> {
        Animal newAnimal = ctx.bodyAsClass(Animal.class);
        if(newAnimal.getAnimal_name() == null || newAnimal.getAnimal_name().trim().isEmpty()) {
            ctx.result("Animal name is required!");
            ctx.status(400);
        } else if (newAnimal.getAnimal_species() == null || newAnimal.getAnimal_species().trim().isEmpty()) {
            ctx.result("Species name is required!!");
            ctx.status(400);
        } else if (newAnimal.getAnimal_age() <= 0) {
            ctx.result("Animal age must be greater than 0");
            ctx.status(400);
        } else {
            Animal insertedAnimal = aDAO.insertAnimal(newAnimal);
            ctx.status(201);
            ctx.json(insertedAnimal);
        }
    };

    //updateAnimalSpeciesHandler

    public Handler updateAnimalSpeciesHandler = ctx -> {
        int animal_id= Integer.parseInt(ctx.pathParam("id"));
        String updatedSpecies = ctx.body();
        if(updatedSpecies.trim().isEmpty()) {
            ctx.result("Species name is required!!");
            ctx.status(400);
        } else {
            ctx.status(200);
            String newSpecies = aDAO.updateAnimalSpecies(animal_id, updatedSpecies);
            ctx.result("Animal Id " + animal_id + " species updated to " + newSpecies);
        }
    };

    //killAnimalHandler
    public Handler deleteAnimalHandler = ctx -> {
        int animal_id = Integer.parseInt(ctx.pathParam("id"));

        int rowsAffected = aDAO.deleteAnimalById(animal_id); // Get the number of rows deleted

        if (rowsAffected == 0){
            ctx.status(404);
            ctx.result("Animal with Id " + animal_id + " not found.");
        } else {
            ctx.status(200);
            ctx.result("Animal with Id " + animal_id + " was successfully deleted.");
        }
    };
    //killAllAnimalsHandler
    public Handler deleteAllAnimalsHandler = ctx -> {
        int rowsAffected = aDAO.deleteAllAnimals(); // Get the number of rows deleted
        if (rowsAffected == 0) {
            ctx.status(404);
            ctx.result("No animals were found to delete.");
        } else {
            ctx.status(200);
            ctx.result(rowsAffected + " animals were killed.");
        }
    };
}
