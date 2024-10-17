package com.revature.DAOs;

import com.revature.models.Habitat;

import java.util.ArrayList;

public interface HabitatDAOInterface {

    Habitat getHabitatById(int id);

    Habitat insertHabitat(Habitat hab);

    int updateHabitatCapacity(int id, int newCapacity);

    ArrayList<Habitat> getAllHabitats();
}
