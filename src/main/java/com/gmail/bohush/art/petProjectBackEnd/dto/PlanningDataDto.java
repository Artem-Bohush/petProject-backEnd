package com.gmail.bohush.art.petProjectBackEnd.dto;

import lombok.Data;

@Data
public class PlanningDataDto {
    private double recordId;
    private double totalCategoryOutcome;
    private double recordOutcome;
    private String categoryName;
    private String descr;
    private double categoryLimit;
    private double percent;
}
