package com.gmail.bohush.art.petProjectBackEnd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lim")
    private String lim;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="category", cascade=CascadeType.ALL)
    private List<Record> records;

    public Category() {
    }

    public Category(String name, String lim, User user) {
        this.name = name;
        this.lim = lim;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lim='" + lim + '\'' +
                ", user=" + user +
                '}';
    }
}
