package com.nirmal.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirmal.dto.CategoryDto;
import com.nirmal.dto.CategoryResponse;
import com.nirmal.model.Category;
import com.nirmal.service.CategoryService;

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
}
