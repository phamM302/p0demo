package com.revature;
import com.revature.controllers.AnimalController;
import com.revature.controllers.AuthController;
import com.revature.controllers.HabitatController;
import io.javalin.Javalin;
import io.javalin.http.UnauthorizedResponse;

public class Launcher {
    public static void main(String[] args) {
        var app = Javalin.create().start(7000);

        app.before("/animals", ctx -> {
            if(AuthController.ses == null) {
                throw new IllegalArgumentException("You must login before doing this!");
            }
        });

        app.before("/habitats", ctx -> {
            if(AuthController.ses == null) {
                throw new IllegalArgumentException("You must login before doing this!");
            }
        });

        app.get("/", ctx -> ctx.result("hello javalin and postman"));

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        //controller
        AnimalController ac = new AnimalController();

        //
        HabitatController hc = new HabitatController();

        AuthController acc = new AuthController();

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
        app.delete("/animals", ac.deleteAllAnimalsHandler);

        //get all habitats
        app.get("/habitats", hc.getAllHabitatsHandler);

        //get habitat by ID
        app.get("/habitats/{id}", hc.getHabitatByIdHandler);

        //update habitat capacity
        app.patch("/habitats/{id}", hc.updateHabitatCapacityHandler);

        app.post("/habitats", hc.insertHabitatHandler);

        app.post("/auth", acc.loginHandler);
    }
}
