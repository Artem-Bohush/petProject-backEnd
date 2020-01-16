package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.RecordType;

public interface RecordTypeService {

    void save(RecordType recordType);

    RecordType findByName(String name);
}
