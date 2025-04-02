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
import com.nirmal.exception.ResourceNotFoundException;
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
		
		// we have implemented insert and update in this same function, if id is present we will run update else we will run save.
		if(ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		}
		else {
			updateCategory(category);
		}
		
		Category savedCategory =  categoryDao.save(category);
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}

	
	private void updateCategory(Category category) {
		Optional<Category> categoryToUpdate = categoryDao.findById(category.getId());
		if(categoryToUpdate.isPresent()) {
			Category cat = categoryToUpdate.get();
			category.setCreatedBy(cat.getCreatedBy());
			category.setCreatedOn(cat.getCreatedOn());
			category.setIsDeleted(cat.getIsDeleted());
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
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
//		CoreJavaCategoryDaoImpl cDao = new CoreJavaCategoryDaoImpl();
//		List<Category> categories = cDao.findByIsActiveTrue();
		List<CategoryResponse> catResp = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return catResp;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		Optional<Category> findCategoryById = categoryDao.findById(id);
		// or if you directly want, categoryDao.findByIdAndIsDeletedFalse(id);  you can also use this as this is better
		// as there is no unnecessary data transfer
//		CategoryDto cat = mapper.map(category, CategoryDto.class);
		if(findCategoryById.isPresent() && !findCategoryById.get().getIsDeleted()) {
			Category category = findCategoryById.get();
			return mapper.map(category, CategoryDto.class);
		}
		else {
			throw new ResourceNotFoundException("Category not found with id " + id);
		}
		
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
