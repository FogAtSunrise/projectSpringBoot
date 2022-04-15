package com.stoneshop.project.Dao;



import com.stoneshop.project.model.Category;
import com.stoneshop.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICrudService<Category>{
    @Autowired
    CategoryRepository sellerRepository;

    @Override
    public Optional<Category> find(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public void save(Category model) {
        Category save = sellerRepository.save(model);
    }

    @Override
    public void delete(Category model) {
        sellerRepository.delete(model);
    }

    public void deleteById(Long id){
        delete(find(id).get());
    }


    @Override
    public List<Category> getAll() {
        return new ArrayList<>(sellerRepository.findAll());
    }
}
