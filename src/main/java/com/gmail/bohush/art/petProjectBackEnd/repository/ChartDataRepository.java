package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.ChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChartDataRepository extends JpaRepository<ChartData, Long> {

    @Query("SELECT u FROM ChartData u where u.id = :id")
    ChartData customFindById(@Param("id") Long id);
}
