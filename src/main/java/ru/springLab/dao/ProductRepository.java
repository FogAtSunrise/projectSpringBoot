package ru.springLab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springLab.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity, Long> {

    /**
     * Метод поиска города по имени
     *
     * @param name имя города
     * @return сущность города
     */
    ProductEntity findByName(String name);

    /**
     * Метод поиска городов по фрагменту имени, игнорируя регистр
     *
     * @param nameFragment фрагмент имени
     * @return список городов
     */
    List<ProductEntity> findByNameIgnoreCaseContaining(String nameFragment);

}
