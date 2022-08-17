package com.project.spring.services;

import java.util.List;

import com.project.spring.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto dto);
	CategoryDto updateCategory(CategoryDto dto, Integer catId);
	CategoryDto getCategory(Integer catId);
	List<CategoryDto> getCategories();
	void deleteCategory(Integer catId);
}
