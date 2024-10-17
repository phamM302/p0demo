package com.revature.controllers;

import com.revature.DAOs.HabitatDAO;
import com.revature.models.Habitat;
import io.javalin.http.Handler;

public class HabitatController {

    HabitatDAO hDAO = new HabitatDAO();

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

    public Handler updateHabitatCapacity = ctx -> {
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
}
