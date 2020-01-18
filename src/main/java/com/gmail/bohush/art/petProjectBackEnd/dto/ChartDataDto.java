package com.gmail.bohush.art.petProjectBackEnd.dto;

import com.gmail.bohush.art.petProjectBackEnd.entity.ChartData;
import com.gmail.bohush.art.petProjectBackEnd.entity.Record;
import lombok.Data;

@Data
public class ChartDataDto {
    private double y;
    private double percent;
    private String label;

    public static ChartDataDto toChartDataDto(ChartData chartData) {
        ChartDataDto chartDataDto = new ChartDataDto();
        chartDataDto.setY(chartData.getY());
        chartDataDto.setLabel(chartData.getLabel());
        return chartDataDto;
    }
}
