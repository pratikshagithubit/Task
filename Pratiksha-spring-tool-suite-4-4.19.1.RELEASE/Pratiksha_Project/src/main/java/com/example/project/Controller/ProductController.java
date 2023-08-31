package com.example.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Product;
import com.example.project.Services.ProductServices;
import com.example.project.payload.ProductDto;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductServices productservices;

	@PostMapping("/create/{catid}")
	@ResponseBody
	// product/create
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product,@PathVariable int catid) {
		//System.out.println(product.getProduct_name());
		ProductDto createProduct = productservices.createProduct(product,catid);
		return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
	}

	// view Product
	@GetMapping("View")
	public ResponseEntity<List<ProductDto>>viewAllProduct() {
		List<ProductDto> viewAll = productservices.viewAll();
		return new ResponseEntity<List<ProductDto>>(viewAll, HttpStatus.ACCEPTED);
	}

	// View ProductById
	@GetMapping("view/{productId}")
	public ResponseEntity<ProductDto> viewProductById(@PathVariable int productId) {
		ProductDto viewById = productservices.viewProductById(productId);
		return new ResponseEntity<ProductDto> (viewById,HttpStatus.OK);
	}

	// delete product
	@DeleteMapping("/del/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		productservices.deleteProduct(productId);
		return new ResponseEntity<String>("Product Delete",HttpStatus.OK);
	}

	// update Product
	@PutMapping("/update/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId, @RequestBody ProductDto newproduct) {
		ProductDto updateProduct = productservices.updateProduct(productId, newproduct);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.ACCEPTED) ;

	}
}
