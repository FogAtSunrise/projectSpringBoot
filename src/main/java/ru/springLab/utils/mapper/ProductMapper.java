package ru.springLab.utils.mapper;

import ru.springLab.dto.CityModel;
import ru.springLab.dto.ProductModel;
import ru.springLab.entity.CityEntity;
import ru.springLab.entity.ProductEntity;
import ru.springLab.form.CityForm;
import ru.springLab.form.ProductForm;
import ru.springLab.utils.ImgTransformationUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    /**
     * Статический метод преобразования сущности продукта в модель

     */
    public static ProductModel entityToModel(ProductEntity productEntity) {
        ImgTransformationUtils imgTransformationUtils = ImgTransformationUtils.getInstance();
        return ProductModel.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .category(productEntity.getCategory())
                .photo("data:image/jpeg;base64," + imgTransformationUtils.byteToBase64(productEntity.getPhoto()))
                .size(productEntity.getSize())
                .price(productEntity.getPrice())
                .build();
    }

    /**
     * Статический метод преобразования списка сущностей продуктов в список канальных моделей
     *

     */
    public static List<ProductModel> entityListToModel(List<ProductEntity> productEntities) {
        return productEntities.stream().map(ProductMapper::entityToModel).collect(Collectors.toList());
    }

    /**
     * Статический метод преобразования формы ввода продукта в сущность
     *
     */
    public static ProductEntity formToEntity(ProductForm productForm) {
        return ProductEntity.builder()
                .id(productForm.getId())
                .name(productForm.getName())
                .description(productForm.getDescription())
                .size(productForm.getSize())
                .price(productForm.getPrice())
                .build();
    }

}
