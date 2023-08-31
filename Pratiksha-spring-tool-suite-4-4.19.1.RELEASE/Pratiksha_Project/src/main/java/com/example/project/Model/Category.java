package com.example.project.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     private int categoryId;
     private String title;
     @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade =CascadeType.ALL )
     private Set<Product> product;
     
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int categorId, String title) {
		super();
		this.categoryId = categorId;
		this.title = title;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Set<Product>getProduct(Set<Product> product){
		return product;
	}
	
	public void setProduct(Set<Product> product) {
		this.product=product;
	}
	
	
	
	
	
}
