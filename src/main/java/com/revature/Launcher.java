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

        //get habitat by ID
        app.get("/habitats/{id}", hc.getHabitatByIdHandler);
    }
}
