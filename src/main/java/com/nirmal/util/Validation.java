package com.nirmal.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.nirmal.dto.CategoryDto;
import com.nirmal.exception.ValidationException;

@Component
public class Validation { 
	
	public void categoryValidation(CategoryDto categoryDto) {
		
		Map<String, Object> error = new LinkedHashMap<>();
		
		// validation for name field
		if(ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("category object/JSON should not be empty");
		}
		else {
			if(ObjectUtils.isEmpty(categoryDto.getName())) {
				error.put("name", "name field is empty");
			}else {
				if(ObjectUtils.isEmpty(categoryDto.getName().length() > 100)) {
					error.put("name", "name can have max length of 100 chars");
			}
		}
	}
		
		if(!error.isEmpty()) {
			throw new ValidationException(error);
		}
}
	}
