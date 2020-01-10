package com.gmail.bohush.art.petProjectBackEnd.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="records")
public class Record {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "sum")
    private String sum;

    @Column(name = "date")
    private String date;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name="id_record_types")
//    private RecordType recordType;
//
//    @ManyToOne
//    @JoinColumn(name="id_categories")
//    private Category category;
}
