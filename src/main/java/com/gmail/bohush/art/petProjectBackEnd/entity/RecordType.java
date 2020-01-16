package com.gmail.bohush.art.petProjectBackEnd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="record_types")
public class RecordType {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="type", cascade=CascadeType.ALL)
    private List<Record> records;

    public RecordType() {
    }

    public RecordType(String name) {
        this.name = name;
    }
}
