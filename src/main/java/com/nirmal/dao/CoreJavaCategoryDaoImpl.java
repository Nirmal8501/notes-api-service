package com.nirmal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.nirmal.model.Category;

public class CoreJavaCategoryDaoImpl implements CategoryDao{

	// ignore this class this is just for learning, not used in project.
	// This is written in core java, like what we would have done if we didnt have JPA for db
	
	private Connection connection;
	private final String INSERT_CATEGORY_INTO_DB = "Insert into category (name, description, isActive) values (?,?,?)";
	private final String GET_ALL_CATEGORIES = "Select * from category";
	private final String GET_CATEGORY_BY_ID = "Select * from category where id=?";
	
	CoreJavaCategoryDaoImpl(Connection connection){
		this.connection = connection;
	}
	
	public boolean insertCategoryToDb(Category category) {
		try(PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY_INTO_DB)){
			ps.setString(1, category.getName());
			ps.setString(2, category.getDescription());
			ps.setBoolean(3, category.getIsActive());
			return ps.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception while inserting in db...");
			return false;
		}
	}
	
	
	public List<Category> findAllCategories(){
		List<Category> categories = new ArrayList<>();
		
		try(Statement ps = connection.createStatement()){
			ResultSet rs = ps.executeQuery(GET_ALL_CATEGORIES);
			while(rs.next()) {
//				Category category = new Category(rs.getInt("id"), rs.getString("name"))
//				categories.add(category)
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception while inserting in db...");
			// return some empty list collection
		}
		return categories;
	}
	
	public Optional<Category> findCategoryById(int id) {
		Category category = null;
		try(PreparedStatement ps = connection.prepareStatement(GET_CATEGORY_BY_ID)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery(GET_CATEGORY_BY_ID);
			if(rs.next()) {
				 category = new Category();
				//return new Category() Category(rs.getString(name)..... and so on)
			}
			else Optional.ofNullable(category);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	
	
	
	
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Category> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByIsActiveTrue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
