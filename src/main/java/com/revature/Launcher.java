package com.revature;
import com.revature.controllers.AnimalController;
import com.revature.controllers.HabitatController;
import io.javalin.Javalin;
public class Launcher {
    public static void main(String[] args) {
        var app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("hello javalin and postman"));

        //controller
        AnimalController ac = new AnimalController();

        //
        HabitatController hc = new HabitatController();

        //get all animals
        app.get("/animals", ac.getAnimalsHandler);

        //insert animal
        app.post("/animals", ac.insertAnimalHandler);

        //get animals by Habitat Id
        app.get("/animals/{id}", ac.getAnimalsByIdHandler);

        //update species name by id
        app.patch("/animals/{id}", ac.updateAnimalSpeciesHandler);

        //delete animal by id
        app.delete("/animals/{id}", ac.deleteAnimalHandler);

        //delete all animals
        app.delete("/animals/", ac.deleteAllAnimalsHandler);

        //get habitat by ID
        app.get("/habitats/{id}", hc.getHabitatByIdHandler);

        //update habitat capacity
        app.patch("/habitats/{id}", hc.updateHabitatCapacityHandler);

        app.post("/habitats", hc.insertHabitatHandler);
    }
}
