package com.revature.DAOs;

import com.revature.models.Habitat;

public interface HabitatDAOInterface {

    Habitat getHabitatById(int id);

    int updateHabitatCapacity(int id, int newCapacity);
}
