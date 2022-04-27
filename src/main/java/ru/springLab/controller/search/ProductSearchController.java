package ru.springLab.controller.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springLab.dto.ProductModel;
import ru.springLab.form.ProductForm;
import ru.springLab.service.search.ProductSearchService;

import java.util.List;

@Controller
@Slf4j
public class ProductSearchController {
    @Autowired
    ProductSearchService productSearchService;

    /**
     * Метод загрузки страницы всех городов
     *
     * @param model модель для передачи данных на страницу
     * @return страница городов
     */
    @GetMapping("/products")
    public String loadProductsPage(Model model) {
        log.info("[GET - /products]\tEntered loadProductsPage method");
        List<ProductModel> products = productSearchService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productForm", new ProductForm());
        log.info("[GET - /products]\tLoading products page");
        return "products";
    }

    /**
     * Метод загрузки страницу конкретного города с требуемым id
     *
     * @param id    id города
     * @param model модель для передачи данных на страницу
     * @return страница города
     */
    @GetMapping("/products/{id}")
    public String loadProductDetailsPage(@PathVariable Long id, Model model) {
        log.info("[GET - /products/" + id + "]\tEntered loadProductsDetailsPage method");
        model.addAttribute("product", productSearchService.findById(id));
        model.addAttribute("productForm", new ProductForm());
        log.info("[GET - /products/" + id + "]\tLoading product_details page");
        return "product_details";
    }

    /**
     * Метод обработки запроса на поиск городов по фрагменту имени
     *
     * @param nameFragmentJSON JSON с фрагментом имени
     * @return объект со статусом ответа на запрос и передаваемыми данными от сервера(список найденных городов)
     */
    @PostMapping("/products/find")
    @ResponseBody
    public ResponseEntity<?> findProductsByStringContaining(@RequestBody String nameFragmentJSON) {
        log.info("[POST - /products/find]\tEntered findProductsByStringContaining method");
        try {
            String nameFragment = new ObjectMapper()
                    .readValue(nameFragmentJSON, ObjectNode.class)
                    .get("text")
                    .textValue();
            List<ProductModel> foundProducts = productSearchService.findByNameContaining(nameFragment);
            log.info("[POST - /products/find]\tReturning found products by fragment " + nameFragment);
            return ResponseEntity.ok(foundProducts);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
