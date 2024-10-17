package com.revature.controllers;

import com.revature.DAOs.HabitatDAO;
import com.revature.models.Animal;
import com.revature.models.Habitat;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class HabitatController {

    HabitatDAO hDAO = new HabitatDAO();

    public Handler getAllHabitatsHandler = ctx -> {
        ArrayList<Habitat> habitats = hDAO.getAllHabitats();
        ctx.json(habitats);
        ctx.status(200);
    };

    public Handler getHabitatByIdHandler = ctx -> {
        int habitat_id = Integer.parseInt(ctx.pathParam("id"));

        Habitat habitat = hDAO.getHabitatById(habitat_id);
        //make sure role isnt null
        if (habitat_id <= 0) {
            ctx.result("Habitat ID must be greater than 0");
            ctx.status(400);
        } else if (habitat == null) {
            ctx.result("Habitat ID: " + habitat_id + " not found");
            ctx.status(404);
        } else {
            ctx.json(habitat);
            ctx.status(200); //200 ok
        }
    };

    public Handler updateHabitatCapacityHandler = ctx -> {
        int habitat_id = Integer.parseInt(ctx.pathParam("id"));
        int capacity = Integer.parseInt((ctx.body()));

        if(capacity <= 0) {
            ctx.result("Capacity must be greater than 0");
            ctx.status(400);
        } else {
            int newCapacity = hDAO.updateHabitatCapacity(habitat_id, capacity);
            ctx.result("Habitat ID " + habitat_id + " capacity updated to " + newCapacity);
            ctx.status(200);
        }
    };

    /*
    public Handler insertHabitatHandler
    * */
    public Handler insertHabitatHandler = ctx -> {
        Habitat newHabitat = ctx.bodyAsClass(Habitat.class);
        if(newHabitat.getHabitat_name() == null || newHabitat.getHabitat_name().trim().isEmpty()) {
            ctx.result("Habitat name is required!");
            ctx.status(400);
        } else if (newHabitat.getHabitat_capacity() <= 0) {
            ctx.result("Habitat needs to have 1 capacity");
            ctx.status(400);
        } else {
            Habitat insertedHabitat = hDAO.insertHabitat(newHabitat);
            ctx.status(201);
            ctx.json(insertedHabitat);
        }
    };

}
