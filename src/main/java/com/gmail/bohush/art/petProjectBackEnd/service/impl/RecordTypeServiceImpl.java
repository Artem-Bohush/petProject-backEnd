package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.RecordType;
import com.gmail.bohush.art.petProjectBackEnd.repository.RecordRepository;
import com.gmail.bohush.art.petProjectBackEnd.repository.RecordTypeRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecordTypeServiceImpl implements RecordTypeService {

    private final RecordTypeRepository recordTypeRepository;

    public RecordTypeServiceImpl(RecordTypeRepository recordTypeRepository) {
        this.recordTypeRepository = recordTypeRepository;
    }

    @Override
    public void save(RecordType recordType) {
        recordTypeRepository.save(recordType);
    }

    @Override
    public RecordType findByName(String name) {
        return recordTypeRepository.findByName(name);
    }
}
