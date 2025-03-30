package com.nirmal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		List<Category> categories = categoryDao.findByIsDeletedFalse();
		
		List<CategoryDto> categoryList = categories.stream().map(category -> mapper.map(category, CategoryDto.class)).toList();
		
		return categoryList;
	}

	@Override
	public List<CategoryResponse> getActiveCategories() {
		List<Category> categories = categoryDao.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponse> catResp = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return catResp;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Optional<Category> findCategoryById = categoryDao.findById(id);
		// or if you directly want, categoryDao.findByIdAndIsDeletedFalse(id);  you can also use this as this is better
		// as there is no unnecessary data transfer
//		CategoryDto cat = mapper.map(category, CategoryDto.class);
		if(findCategoryById.isPresent() && !findCategoryById.get().getIsDeleted()) {
			Category category = findCategoryById.get();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> findCategoryById = categoryDao.findById(id);
		
		if(findCategoryById.isPresent()) {
			Category category = findCategoryById.get();
			category.setIsDeleted(true);
			categoryDao.save(category);
			return true;
		}
		return false;
	}

}
