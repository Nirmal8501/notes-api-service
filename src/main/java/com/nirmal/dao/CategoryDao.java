package com.nirmal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	List<Category> findByIsActiveTrueAndIsDeletedFalse();

	List<Category> findByIsDeletedFalse();

	List<Category> findByIsActiveTrue();

}
