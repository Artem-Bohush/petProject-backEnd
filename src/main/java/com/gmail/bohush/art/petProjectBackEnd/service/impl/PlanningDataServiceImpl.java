package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.PlanningData;
import com.gmail.bohush.art.petProjectBackEnd.repository.PlanningDataRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.PlanningDataService;
import org.springframework.stereotype.Service;

@Service
public class PlanningDataServiceImpl implements PlanningDataService {

    private final PlanningDataRepository planningDataRepository;

    public PlanningDataServiceImpl(PlanningDataRepository planningDataRepository) {
        this.planningDataRepository = planningDataRepository;
    }

    @Override
    public void save(PlanningData planningData) {
        planningDataRepository.save(planningData);
    }

    @Override
    public PlanningData findById(Long id) {
        return planningDataRepository.customFindById(id);
    }
}
