package ru.springLab.service.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springLab.dao.ProductRepository;
import ru.springLab.entity.ProductEntity;
import ru.springLab.form.ProductForm;
import ru.springLab.utils.ImgTransformationUtils;
import ru.springLab.utils.mapper.ProductMapper;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ProductCrudService {

    @Autowired
    ProductRepository productRepository;

    /**
     * Процедура сохранения нового  изделия в БД
     *

     */
    @Transactional
    public void save(ProductForm productForm) throws IOException {
        log.info("[ProductCrudService]\tEntered save method");
        ProductEntity product = ProductMapper.formToEntity(productForm);
        if (productForm.getPhoto().getSize() == 0)
            product.setPhoto(ImgTransformationUtils.getInstance().getByteEmptyPicture());
        else
            product.setPhoto(productForm.getPhoto().getBytes());
        productRepository.save(product);
        log.info("[ProductCrudService]\tSuccessfully saved product");
    }

    /**
     * Процедура удаления города по ID из БД
     *
     * @param id ID удаляемого города
     */
    @Transactional
    public void deleteById(Long id) {
        log.info("[ProductCrudService]\tEntered deleteById method");
        productRepository.deleteById(id);
        log.info("[ProductCrudService]\tSuccessfully deleted product with id = " + id);
    }

    /**
     * Процедура обновления города в БД
     *
     * @param productForm форма с новыми данными города
     * @throws IOException исключение в случае ошибки доступа (при сбое временного хранилища)
     */
    @Transactional
    public void update(ProductForm productForm) throws IOException {
        log.info("[ProductCrudService]\tEntered update method");
        Optional<ProductEntity> product = productRepository.findById(productForm.getId());
        if (product.isPresent()) {
            ProductEntity updatedProduct = ProductMapper.formToEntity(productForm);
            if (productForm.getPhoto().getSize() == 0)
                updatedProduct.setPhoto(product.get().getPhoto());
            else
                updatedProduct.setPhoto(productForm.getPhoto().getBytes());
            productRepository.save(updatedProduct);
            log.info("[ProductCrudService]\tSuccessfully updated product with id = " + productForm.getId());
        }
        log.info("[ProductCrudService]\tExit update method");
    }

}
