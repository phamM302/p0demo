package com.revature.DAOs;
import com.revature.models.Animal;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface AnimalDAOInterface {

    Animal insertAnimal(Animal ani);

    ArrayList<Animal> getAllAnimals();

    ArrayList<Animal> getAnimalsByHabitatId(int id);

    String updateAnimalSpecies(int id, String updatedSpecies);

    int deleteAnimalById(int id);

    int deleteAllAnimals();
}
