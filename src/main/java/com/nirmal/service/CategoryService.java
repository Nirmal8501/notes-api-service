package com.nirmal.service;

import java.util.List;

import com.nirmal.dto.CategoryDto;
import com.nirmal.dto.CategoryResponse;
import com.nirmal.model.Category;

public interface CategoryService {
	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> getAllCategory();
	public List<CategoryResponse> getActiveCategories();
	public CategoryDto getCategoryById(Integer id) throws Exception;
	public Boolean deleteCategoryById(Integer id);
}
