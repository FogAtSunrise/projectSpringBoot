package ru.springLab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ProductModel {
    /**
     * ID
     */
    private Long id;

    /**
     * Имя
     */
    private String name;
    /**
     * Описание
     */
    private String description;

    /**
     * категория
     */
    private String category;

    /**
     * размер
     */
    private String size;


    /**
     * Фото
     */
    private String photo;

    /**
     * Цена
     */
    private String price;
}
