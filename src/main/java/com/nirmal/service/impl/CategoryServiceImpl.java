package com.nirmal.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nirmal.dao.CategoryDao;
import com.nirmal.dto.CategoryDto;
import com.nirmal.dto.CategoryResponse;
import com.nirmal.model.Category;
import com.nirmal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		// and so on for other fields...   this is manual, but we will use DtoMapper
		
		Category category =  mapper.map(categoryDto, Category.class);
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savedCategory =  categoryDao.save(category);
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryDao.findAll();
		
		List<CategoryDto> categoryList = categories.stream().map(category -> mapper.map(category, CategoryDto.class)).toList();
		
		return categoryList;
	}

	@Override
	public List<CategoryResponse> getActiveCategories() {
		List<Category> categories = categoryDao.findByIsActiveTrue();
		List<CategoryResponse> catResp = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return catResp;
	}

}
