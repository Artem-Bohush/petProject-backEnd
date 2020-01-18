package com.gmail.bohush.art.petProjectBackEnd.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="records")
public class Record {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "sum")
    private double sum;

    @Column(name = "planning_date")
    private String planningDate;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="record_types_id")
    private RecordType type;

    @ManyToOne
    @JoinColumn(name="categories_id")
    private Category category;
}
