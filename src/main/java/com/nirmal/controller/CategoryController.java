package com.nirmal.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.dto.CategoryDto;
import com.nirmal.dto.CategoryResponse;
import com.nirmal.exception.ResourceNotFoundException;
import com.nirmal.model.Category;
import com.nirmal.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
		Boolean res =  categoryService.saveCategory(categoryDto);
		if(res) return new ResponseEntity<>("save success", HttpStatus.CREATED);
		return new ResponseEntity<>("unable to save category", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCategories(){
		List<CategoryDto> categoryList =  categoryService.getAllCategory();
		if(ObjectUtils.isEmpty(categoryList)) return ResponseEntity.noContent().build();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	@GetMapping("/active-categories")
	public ResponseEntity<?> getActiveCategories(){
		List<CategoryResponse> categoryList =  categoryService.getActiveCategories();
		if(ObjectUtils.isEmpty(categoryList)) return ResponseEntity.noContent().build();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id){
		try {
			CategoryDto category = categoryService.getCategoryById(id);
			if(ObjectUtils.isEmpty(category)) return new ResponseEntity<>("Category not found with id: "+id, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}
		catch(ResourceNotFoundException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
		Boolean deleted = categoryService.deleteCategoryById(id);
		if(deleted) return new ResponseEntity<>("Delete Success", HttpStatus.OK);
		return new ResponseEntity<>("Delete failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
