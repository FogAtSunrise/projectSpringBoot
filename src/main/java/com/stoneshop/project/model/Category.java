package com.stoneshop.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "category")
/***
 * Класс JPA-сущности для таблицы продавцов
 */
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category")
    List<Product> productList;

}
