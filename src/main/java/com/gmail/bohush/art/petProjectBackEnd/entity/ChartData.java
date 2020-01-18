package com.gmail.bohush.art.petProjectBackEnd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="chart_data")
public class ChartData {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "category_name")
    private String label;

    @Column(name = "total_outcome")
    private double y;

//    @Column(name = "percent")
//    private double percent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
}
