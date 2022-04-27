package ru.springLab.service.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springLab.dao.CityRepository;
import ru.springLab.dao.ProductRepository;
import ru.springLab.dto.CityModel;
import ru.springLab.dto.ProductModel;
import ru.springLab.entity.CityEntity;
import ru.springLab.entity.ProductEntity;
import ru.springLab.utils.mapper.CityMapper;
import ru.springLab.utils.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductSearchService {
    @Autowired
    ProductRepository productRepository;

    /**
     * Метод поиска всех городов
     *
     * @return список найденных городов, отсортированных по имени
     */
    @Transactional(readOnly = true)
    public List<ProductModel> findAll() {
        log.info("[ProductSearchService]\tEntered findAll method");
        List<ProductEntity> productEntities = productRepository.findAll(Sort.by("name"));
        log.info("[ProductSearchService]\tReturning list of cities");
        return ProductMapper.entityListToModel(productEntities);
    }

    /**
     * Метод поиска города по ID. Переводим доменную модель в канальную
     *
     * @param id ID города
     * @return канальная модель города
     */
    @Transactional(readOnly = true)
    public ProductModel findById(Long id) {
        log.info("[ProductSearchService]\tEntered findById method");
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        log.info("[ProductSearchService]\tReturning =product with id = " + id);
        return productEntity.map(ProductMapper::entityToModel).orElse(null);
    }

    /**
     * Метод поиска города по имени
     *
     * @param name имя города
     * @return модель найденного города
     */
    @Transactional(readOnly = true)
    public ProductModel findByName(String name) {
        log.info("[ProductSearchService]\tEntered findByName method");
        ProductEntity product = productRepository.findByName(name);
        if (product == null) {
            log.info("[Product SearchService]\tReturning null");
            return null;
        }
        log.info("[ProductSearchService]\tReturning found city model");
        return ProductMapper.entityToModel(productRepository.findByName(name));
    }

    @Transactional(readOnly = true)
    public List<ProductModel> findByNameContaining(String nameFragment) {
        log.info("[ProductSearchService]\tEntered findByNameContaining method");
        List<ProductEntity> foundProducts = productRepository.findByNameIgnoreCaseContaining(nameFragment);
        log.info("[ProductSearchService]\tfindByNameContaining method done");
        return ProductMapper.entityListToModel(foundProducts);
    }
}
