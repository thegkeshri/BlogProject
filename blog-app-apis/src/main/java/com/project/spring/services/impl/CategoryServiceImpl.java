package com.project.spring.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.entities.Category;
import com.project.spring.exceptions.ResourceNotFound;
import com.project.spring.payloads.CategoryDto;
import com.project.spring.repositories.CategoryRepo;
import com.project.spring.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category cat=this.modelMapper.map(dto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto, Integer catId) {
		Category cat=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFound("Category", "Category Id", catId));
		cat.setCatDesc(dto.getCatDesc());
		cat.setCatTitle(dto.getCatTitle());
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		Category cat=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFound("Category", "Category Id", catId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> list=this.categoryRepo.findAll();
		List<CategoryDto> dtoList=list.stream().map(cat->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category cat=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFound("Category", "Category Id", catId));
		this.categoryRepo.delete(cat);
	}

}
