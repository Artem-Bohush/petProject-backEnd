package com.gmail.bohush.art.petProjectBackEnd.service.impl;

import com.gmail.bohush.art.petProjectBackEnd.entity.ChartData;
import com.gmail.bohush.art.petProjectBackEnd.repository.ChartDataRepository;
import com.gmail.bohush.art.petProjectBackEnd.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartDataServiceImpl implements ChartDataService {

    private final ChartDataRepository chartDataRepository;

    public ChartDataServiceImpl(ChartDataRepository chartDataRepository) {
        this.chartDataRepository = chartDataRepository;
    }

    @Override
    public void save(ChartData chartData) {
        chartDataRepository.save(chartData);
    }

    @Override
    public void delete(ChartData chartData) {
        chartDataRepository.delete(chartData);
    }

    @Override
    public ChartData findById(Long id) {
        return chartDataRepository.customFindById(id);
    }
}
