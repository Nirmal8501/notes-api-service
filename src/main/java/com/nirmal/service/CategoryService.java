package com.nirmal.service;

import java.util.List;

import com.nirmal.model.Category;

public interface CategoryService {
	public Boolean saveCategory(Category category);
	public List<Category> getAllCategory();
}
