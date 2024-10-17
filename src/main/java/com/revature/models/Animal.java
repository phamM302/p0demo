package com.revature.models;

public class Animal {

    private int animal_id;
    private String animal_name;
    private String animal_species;
    private int animal_age;
    private Habitat habitat;
    private int habitat_id_fk;

    public Animal() {

    }

    public Animal(String animal_name, String animal_species, int animal_age,  int habitat_id_fk) {
        this.animal_name = animal_name;
        this.animal_species = animal_species;
        this.animal_age = animal_age;
        this.habitat_id_fk = habitat_id_fk;
    }

    public Animal(int animal_id, String animal_name, String animal_species, int animal_age, int habitat_id_fk) {
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.animal_species = animal_species;
        this.animal_age = animal_age;
        this.habitat_id_fk = habitat_id_fk;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_species() {
        return animal_species;
    }

    public void setAnimal_species(String animal_species) {
        this.animal_species = animal_species;
    }

    public int getAnimal_age() {
        return animal_age;
    }

    public void setAnimal_age(int animal_age) {
        this.animal_age = animal_age;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public int getHabitat_id_fk() {
        return habitat_id_fk;
    }

    public void setHabitat_id_fk(int habitat_id_fk) {
        this.habitat_id_fk = habitat_id_fk;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animal_id=" + animal_id +
                ", animal_name='" + animal_name + '\'' +
                ", animal_species='" + animal_species + '\'' +
                ", animal_age=" + animal_age +
                ", habitat=" + habitat +
                ", habitat_id_fk=" + habitat_id_fk +
                '}';
    }
}
