package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
