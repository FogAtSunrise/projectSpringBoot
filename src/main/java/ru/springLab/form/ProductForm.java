package ru.springLab.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductForm {
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
    private MultipartFile photo;

    /**
     * Цена
     */
    private String price;
}
