package com.revature.controllers;

import com.revature.DAOs.AnimalDAO;
import com.revature.models.Animal;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class AnimalController {
    AnimalDAO aDAO = new AnimalDAO();

    public Handler getAnimalsHandler = ctx -> {
        ArrayList<Animal> animals = aDAO.getAllAnimals();
        ctx.json(animals);
        ctx.status(200);
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
}
