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
import com.nirmal.model.Category;
import com.nirmal.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody Category category){
		Boolean res =  categoryService.saveCategory(category);
		if(res) return new ResponseEntity<>("save success", HttpStatus.CREATED);
		return new ResponseEntity<>("unable to save category", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCategories(){
		List<Category> categoryList =  categoryService.getAllCategory();
		if(ObjectUtils.isEmpty(categoryList)) return ResponseEntity.noContent().build();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}
}
