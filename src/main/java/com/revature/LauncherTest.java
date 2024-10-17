package com.revature;

import com.revature.DAOs.AnimalDAO;
import com.revature.DAOs.HabitatDAO;
import com.revature.models.Animal;
import com.revature.models.Habitat;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LauncherTest {
    public static void main(String[] args) {
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :D");
        } catch(SQLException e){
            e.printStackTrace(); //this is what tells us our error message (the red text)
            System.out.println("CONNECTION FAILED D:");
        }

        HabitatDAO hDAO = new HabitatDAO();
        AnimalDAO aDAO = new AnimalDAO();
        Habitat h = hDAO.getHabitatById(2);
        System.out.println(h);

        Animal a = new Animal("Joe", "Dog", 3, 3);
        //System.out.println(aDAO.insertAnimal(a));
        //System.out.println(hDAO.updateHabitatCapacity(3, 6));
        ArrayList<Animal> anis = aDAO.getAllAnimals();

        for(Animal ani : anis) {
            System.out.println(ani);
        }

    }
}
