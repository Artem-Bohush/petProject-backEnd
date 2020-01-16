package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import com.gmail.bohush.art.petProjectBackEnd.repository.RecordRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }
}
