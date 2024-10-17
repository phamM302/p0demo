package com.revature.DAOs;
import com.revature.models.Animal;
import com.revature.models.Habitat;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class AnimalDAO implements AnimalDAOInterface{
    @Override
    public Animal insertAnimal(Animal ani) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //create sql statement
            String sql = "INSERT INTO animals (animal_name, animal_species, animal_age, habitat_id_fk)" +
                    "VALUES (?, ?, ?, ?)";
            //use prepared statement to fill in value of variable
            PreparedStatement ps = conn.prepareStatement(sql);

            //use set method to fill value
            ps.setString(1,ani.getAnimal_name());
            ps.setString(2,ani.getAnimal_species());
            ps.setInt(3,ani.getAnimal_age());
            ps.setInt(4,ani.getHabitat_id_fk());
            //send command to the DB
            ps.executeUpdate();
            //return the new employee object
            return ani;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't insert Animal");
        }
        return null;
    }

    @Override
    public ArrayList<Animal> getAllAnimals() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM animals";
            //statement object instead of prepared because there are no variables
            Statement s = conn.createStatement();
            //execute the query
            ResultSet rs = s.executeQuery(sql);
            //need an arraylist to store our employees to return
            ArrayList<Animal> animals = new ArrayList<>();
            while (rs.next()) {
                Animal a = new Animal(
                        rs.getInt("animal_id"),
                        rs.getString("animal_name"),
                        rs.getString("animal_species"),
                        rs.getInt("animal_age"),
                        rs.getInt("habitat_id_fk")
                );
                HabitatDAO hDAO = new HabitatDAO();
                Habitat habitat = hDAO.getHabitatById(rs.getInt("habitat_id_fk"));

                a.setHabitat(habitat);

                animals.add(a);

            }
            return animals;

        } catch (SQLException e) {
            e.printStackTrace();;
            System.out.println("Couldn't get all Animals");
        }
        //catch-all return
        return null;
    }

    @Override
    public ArrayList<Animal> getAnimalsByHabitatId(int id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM animals WHERE habitat_id_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Animal> animals = new ArrayList<>();
            while (rs.next()) {
                Animal a = new Animal(
                        rs.getInt("animal_id"),
                        rs.getString("animal_name"),
                        rs.getString("animal_species"),
                        rs.getInt("animal_age"),
                        rs.getInt("habitat_id_fk")
                );
                HabitatDAO hDAO = new HabitatDAO();
                Habitat habitat = hDAO.getHabitatById(rs.getInt("habitat_id_fk"));

                a.setHabitat(habitat);

                animals.add(a);

            }
            return animals;
        } catch (SQLException e) {
            e.printStackTrace();;
            System.out.println("Couldn't get Animals by Habitat Id");
        }

        return null;
    }

    @Override
    public String updateAnimalSpecies(int id, String updatedSpecies) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE animals SET animal_species = ? WHERE animal_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updatedSpecies);
            ps.setInt(2, id);

            ps.executeUpdate();
            return updatedSpecies;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update the species");
        }
        return "";
    }

    @Override
    public int deleteAnimalById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM animals WHERE animal_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            // Set the animal ID parameter for deletion
            ps.setInt(1, id);

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't delete the animal");
        }
        return 0;
    }

    @Override
    public int deleteAllAnimals() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM animals";
            PreparedStatement ps = conn.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't delete the animal");
        }
        return 0;
    }
}
