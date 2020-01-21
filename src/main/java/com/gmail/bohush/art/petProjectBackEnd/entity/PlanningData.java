package com.gmail.bohush.art.petProjectBackEnd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="planning_data")
public class PlanningData {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "total_outcome")
    private double totalOutcome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
}
