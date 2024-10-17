package com.revature.DAOs;

import com.revature.models.Habitat;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HabitatDAO implements HabitatDAOInterface{
    @Override
    public Habitat getHabitatById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){

            //A String that represents our SQL query
            //Note the "?", which just means it's a variable we need to fill in
            String sql = "SELECT * FROM habitats WHERE habitat_id = ?";

            //We need a PreparedStatement to fill in the variable (id)
            //It takes the SQL String we made above
            PreparedStatement ps = conn.prepareStatement(sql);

            //We can now use the id parameter to set the variable with ps.set() methods
            ps.setInt(1, id);

            //Execute the query, save the results in ResultSet
            ResultSet rs = ps.executeQuery(); //executing the query stored in the PreparedStatement

            //Extract the data from the ResultSet into a Role object
            //"if there is a value in the next index of the resultset..."
            if(rs.next()){
                //Then extract the data into Java Role object! Using the all-args constructor
                //we can use rs.get() to get values from the ResultSet!
                //NOTE: you must use the exact names found in the DB column
                Habitat habitat = new Habitat(
                        rs.getInt("habitat_id"),
                        rs.getString("habitat_name"),
                        rs.getInt("habitat_capacity")
                );

                //Return the new Role!
                return habitat;

            }

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't get Role by ID");
        }
        return null;
    }
    @Override
    public Habitat insertHabitat(Habitat hab) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //create sql statement
            String sql = "INSERT INTO habitats (habitat_name, habitat_capacity)" +
                    "VALUES (?, ?)";
            //use prepared statement to fill in value of variable
            PreparedStatement ps = conn.prepareStatement(sql);

            //use set method to fill value
            ps.setString(1, hab.getHabitat_name());
            ps.setInt(2,hab.getHabitat_capacity());
            //send command to the DB
            ps.executeUpdate();
            //return the new employee object
            return hab;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't insert Habitat");
        }
        return null;
    }

    @Override
    public int updateHabitatCapacity(int id, int newCapacity) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE habitats SET habitat_capacity = ? WHERE habitat_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, newCapacity);
            ps.setInt(2, id);

            ps.executeUpdate();
            return newCapacity;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update the capacity");
        }
        return 0;
    }
}
