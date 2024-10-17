package com.revature.models;

public class Habitat {
    private int habitat_id;
    private String habitat_name;
    private int habitat_capacity;

    public Habitat() {

    }

    public Habitat(int habitat_id, String habitat_name, int habitat_capacity) {
        this.habitat_id = habitat_id;
        this.habitat_name = habitat_name;
        this.habitat_capacity = habitat_capacity;
    }

    public int getHabitat_id() {
        return habitat_id;
    }

    public void setHabitat_id(int habitat_id) {
        this.habitat_id = habitat_id;
    }

    public String getHabitat_name() {
        return habitat_name;
    }

    public void setHabitat_name(String habitat_name) {
        this.habitat_name = habitat_name;
    }

    public int getHabitat_capacity() {
        return habitat_capacity;
    }

    public void setHabitat_capacity(int habitat_capacity) {
        this.habitat_capacity = habitat_capacity;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "habitat_id=" + habitat_id +
                ", habitat_name='" + habitat_name + '\'' +
                ", habitat_capacity=" + habitat_capacity +
                '}';
    }
}
