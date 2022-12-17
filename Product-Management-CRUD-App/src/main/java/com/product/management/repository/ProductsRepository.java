package com.product.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.management.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

}
