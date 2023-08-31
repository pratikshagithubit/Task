package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}


