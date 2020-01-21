package com.gmail.bohush.art.petProjectBackEnd.service;

import com.gmail.bohush.art.petProjectBackEnd.entity.ChartData;

public interface ChartDataService {

    void save(ChartData chartData);

    void delete(ChartData chartData);

    ChartData findById(Long id);
}
