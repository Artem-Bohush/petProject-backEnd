package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.Category;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT u FROM Record u where u.id = :id")
    Record customFindById(@Param("id") Long id);
}
