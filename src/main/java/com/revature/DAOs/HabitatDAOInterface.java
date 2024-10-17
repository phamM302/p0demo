package com.revature.DAOs;

import com.revature.models.Habitat;

public interface HabitatDAOInterface {

    Habitat getHabitatById(int id);

    Habitat insertHabitat(Habitat hab);

    int updateHabitatCapacity(int id, int newCapacity);
}
