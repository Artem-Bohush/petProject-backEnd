package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.PlanningData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlanningDataRepository extends JpaRepository<PlanningData, Long> {

    @Query("SELECT u FROM PlanningData u where u.id = :id")
    PlanningData customFindById(@Param("id") Long id);

}
