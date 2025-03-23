package com.nirmal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirmal.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
