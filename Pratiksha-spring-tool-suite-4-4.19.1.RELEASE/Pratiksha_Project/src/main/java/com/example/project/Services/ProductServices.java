package com.example.project.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Model.Category;
import com.example.project.Model.Product;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Repository.ProductRepository;
import com.example.project.payload.CategoryDto;
import com.example.project.payload.ProductDto;

@Service
public class ProductServices {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CategoryRepository catRepo;

	public ProductDto createProduct(ProductDto productDto,int catid) {
		//Fetch Category is available or not
		Category cat = this.catRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("This Category id not found Category"));
		
		// ProductDto to Product
		System.out.println(productDto.getProduct_name());
         Product product=toEntity(productDto);
         product.setCategory(cat);
         
         //Save Product into database
         Product save = this.productRepo.save(product);
         
		//Product save = productRepo.save(entity);
		
		//Product to ProductDto
		ProductDto dto=toDto(save);
		return dto;
	}

	public List<ProductDto> viewAll() {
		//ProductDto to Product
		List<Product> findAll = productRepo.findAll();
		List<ProductDto> findAllDto=findAll.stream().map(product->this.toDto(product)).collect(Collectors.toList());
		return findAllDto;
	}
	public ProductDto viewProductById(int pid) {
		Product findById = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException(+pid + "from this product id product not found"));
		ProductDto dto=this.toDto(findById);
		return dto;
	}

	public void deleteProduct(int pid) {
		Product byIdProduct = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException(+pid + "from this product id product not found"));
		productRepo.deleteById(pid);
	}

	public ProductDto updateProduct(int pid, ProductDto newproduct) {
		Product oldp = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException(+pid + "from this product id product not found"));
		
		oldp.setProduct_name(newproduct.getProduct_name());
		oldp.setLive(newproduct.isLive());
		oldp.setStock(newproduct.isStock());
		oldp.setProduct_prize(newproduct.getProduct_prize());
		oldp.setProduct_desc(newproduct.getProduct_desc());
		oldp.setProduct_imageName(newproduct.getProduct_imageName());
		oldp.setProduct_quantity(newproduct.getProduct_quantity());
		Product save = productRepo.save(oldp);
         ProductDto dto = toDto(save);
		return dto;

	}
	//ProductDto to Product
	public Product toEntity(ProductDto pDto) {
		Product p=new Product();
		p.setProduct_id(pDto.getProduct_id());
		p.setProduct_name(pDto.getProduct_name());
		p.setProduct_desc(pDto.getProduct_desc());
		p.setProduct_prize(pDto.getProduct_prize());
		p.setProduct_imageName(pDto.getProduct_imageName());
		p.setLive(pDto.isLive());
		p.setStock(pDto.isStock());
		return p;
	}
	
	//Product to ProductDto
	public ProductDto toDto(Product product) {
		ProductDto pDto=new ProductDto();
		pDto.setProduct_id(product.getProduct_id());
		pDto.setProduct_name(product.getProduct_name());
		pDto.setProduct_desc(product.getProduct_desc());
		pDto.setProduct_prize(product.getProduct_prize());
		pDto.setProduct_imageName(product.getProduct_imageName());
		pDto.setLive(product.isLive());
		pDto.setStock(product.isStock());
		
		//Change Category to CategoryDto
		  CategoryDto catDto=new CategoryDto();
		  catDto.setCategoryId(product.getCategory().getCategoryId());
		  catDto.setTitle(product.getCategory().getTitle());
		
		  //Then Set Category Dto in Product Dto
		  pDto.setCategory(catDto);
		
		return pDto;
		
	}
}
