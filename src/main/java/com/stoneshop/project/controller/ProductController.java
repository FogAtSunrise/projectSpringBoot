package com.stoneshop.project.controller;


import com.stoneshop.project.Dao.CategoryService;
import com.stoneshop.project.Dao.ProductService;
import com.stoneshop.project.model.Category;
import com.stoneshop.project.model.Product;
import com.stoneshop.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller

public class ProductController {
    private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService category;

    /**
     * Просмотр списка всех товаров в системе
     * @param vars
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model vars) {
        /* Заполняем модель для представления */
        vars.addAttribute("products", productService.getAll());
        /* Возвращаем имя шаблона, который надо рендерить */
        return "index";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/e")
    public String empty() {
        /* Возвращаем имя шаблона, который надо рендерить */
        return "empty_template";
    }

    /**
     * Подробный просмотр информации о товаре
     * @param productid id запрашиваемого товара
     * @param vars
     * @return
     */
    @GetMapping("/product/{productid}")
    private String getProduct(@PathVariable("productid") Long productid, Model vars){
        //todo проверка на случай, если файл не найден
        vars.addAttribute("product", productService.find(productid).get());
        return "product_details";
    }

    /**
     * Получение страницы редактирования продукта
     * @param productid
     * @param model
     * @return
     */
    @GetMapping("/edit/{productid}")
    private String editProduct(@PathVariable("productid") Long productid, Model model){
        model.addAttribute("product", productService.find(productid).get());
        model.addAttribute("isCreate", false);
        return "product_edit";
    }

    /**
     * Сохранение результата изменения продукта
     * @param product
     * @param model
     * @param productid
     * @return
     */
    @PostMapping("/edit/{productid}")
    public String editProduct(@ModelAttribute Product product, @ModelAttribute("file") MultipartFile file, Model model, @PathVariable String productid) {
        String photo = uploadFile(file);

        if(photo == null)
            photo = "/img/def.png";
        else photo = "/uploads/" + photo;
        Product original = productService.find(Long.valueOf(productid)).get();
        original.setImage(photo);
        original.setName(product.getName());
        original.setCount(product.getCount());
        original.setDescription(product.getDescription());
        original.setPrice(product.getPrice());
        original.setSize(product.getSize());

        productService.save(original);
        model.addAttribute("product", original);
        return "product_details";
    }
    /**
     * Получение страницы для управления объявлениями
     * @param vars
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/monitor")
    public String monitor(Model vars) {
        vars.addAttribute("products", productService.getAll());
        return "product_monitor";
    }

    /**
     * Удаление объекта
     * @return
     */
    @GetMapping("/delete/{productid}")
    public String delete(@PathVariable String productid, Model vars) {
        productService.deleteById(Long.valueOf(productid));
        return "redirect:/monitor";
    }

    /**
     * Загрузка страницы для добавления объекта
     * @return
     */
    @GetMapping("/create")
    public String create(Model model) {

        List<Category> cat = new ArrayList<>();
        model.addAttribute("cat", cat);
        List<Category> categories = category.getAll();
        model.addAttribute("categories", categories);

        model.addAttribute("product", new Product());
        model.addAttribute("isCreate", true);

        return "product_edit";
    }

    /**
     * Добавление объекта в бд
     * @return
     */
    @PostMapping ("/create")
    public String create(@ModelAttribute Product product,  @ModelAttribute("file") MultipartFile file, Model vars) {
        List<Category> cat = new ArrayList<>();
        vars.addAttribute("cat", cat);
        List<Category> categories = category.getAll();
        vars.addAttribute("categories", categories);
        String photo = uploadFile(file);
        if(photo == null)
            photo = "/img/def.png";
        else photo = "/uploads/" + photo;
        product.setImage(photo);
        //product.setCategory(category.findAll().get(0));
        productService.save(product);
        return "redirect:/monitor";
    }

    private String uploadFile(MultipartFile file){
        if (file.isEmpty())
            return null;
            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // save the file on the local file system
            try {
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileName;

    }
    @PostMapping("/upload")
    public String uploadFile1(@ModelAttribute("file") MultipartFile file) throws IOException {

        // check if file is empty
        if (file.isEmpty()) {
           return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        return "redirect:/";
    }

}