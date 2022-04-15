package com.stoneshop.project.repository;

import com.stoneshop.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * extend crud-repositoruy
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
