package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.PlanningData;

public interface PlanningDataService {

    void save(PlanningData planningData);

    PlanningData findById(Long id);
}
