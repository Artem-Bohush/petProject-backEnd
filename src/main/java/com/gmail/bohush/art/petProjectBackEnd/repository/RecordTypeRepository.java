package com.gmail.bohush.art.petProjectBackEnd.repository;

import com.gmail.bohush.art.petProjectBackEnd.entity.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordTypeRepository extends JpaRepository<RecordType, Long> {
    RecordType findByName(String name);
}
