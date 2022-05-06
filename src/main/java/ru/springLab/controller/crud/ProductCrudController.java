package ru.springLab.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.springLab.form.CityForm;
import ru.springLab.form.ProductForm;
import ru.springLab.service.crud.CityCrudService;
import ru.springLab.service.crud.ProductCrudService;
import ru.springLab.service.search.CitySearchService;
import ru.springLab.service.search.ProductSearchService;

@RestController
@Slf4j
public class ProductCrudController {

    @Autowired
    ProductCrudService productCrudService;

    @Autowired
    ProductSearchService productSearchService;

    /**
     * Метод обработки запроса на сохранение нового изделия
     *

     */
    @PostMapping("/add_product")
    @ResponseBody
    public  RedirectView addProduct(@ModelAttribute("productForm") ProductForm productForm) {
        log.info("[POST - /add_product]\tEntered addProduct method");
        try {
            productCrudService.save(productForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[POST - /add_product]\tNew product added successfully");
        //return
        ResponseEntity.ok(productSearchService.findByName(productForm.getName()));
        return new RedirectView("/products");

    }

    /**
     * Метод обработки запроса на удаление города по ID со страницы товара
     *

     */
    @GetMapping("/delete_product_from_page/{id}")
    public void deleteProductByIdFromProductsPage(@PathVariable Long id) {
        log.info("[GET - /delete_product_from_page/" + id + "]\tEntered deleteProductByIdFromProductsPage method");
        productCrudService.deleteById(id);
        log.info("[GET - /delete_product_from_page/" + id + "]\tdeleteProductByIdFromProductsPage done");
    }

    /**
     * Метод обработки запроса на удаление изделия по ID
     *

     */
    @GetMapping("/delete_product/{id}")
    public RedirectView deleteProductById(@PathVariable Long id) {
        log.info("[GET - /delete_product/" + id + "]\tEntered deleteProductById method");
        productCrudService.deleteById(id);
        log.info("[GET - /delete_product/" + id + "]\tRedirecting to products page");
        return new RedirectView("/products");
    }

    /**
     * Метод обработки запроса на обновление
     *
     */
    @PostMapping("/update_product/{id}")
    public RedirectView updateProductById(@PathVariable Long id, @ModelAttribute ProductForm productForm,
                                       @RequestParam("photoProduct") MultipartFile photoProduct) {
        log.info("[POST - /update_product/" + id + "]\tEntered updateProductById method");
        try {
            productForm.setId(id);
            productForm.setPhoto(photoProduct);
            productCrudService.update(productForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[POST - /update_product/" + id + "]\tRedirecting to product_details page");
        return new RedirectView("/products/" + id);
    }

}
