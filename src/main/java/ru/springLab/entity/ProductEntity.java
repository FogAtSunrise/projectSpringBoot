package ru.springLab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "product")
public class ProductEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    /**
     * Описание
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * категория
     */
    @Column(name = "category", columnDefinition = "TEXT")
    private String category;

    /**
     * размер
     */
    @Column(name = "size", columnDefinition = "TEXT")
    private String size;


   @Column(name = "photo")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;




    /**
     * Цена
     */
    @Column(name = "price", columnDefinition = "TEXT")
    private String price;

}
