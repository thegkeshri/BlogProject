package com.project.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.payloads.ApiResponse;
import com.project.spring.payloads.CategoryDto;
import com.project.spring.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto){
		CategoryDto resp=this.categoryService.createCategory(dto);
		return new ResponseEntity<>(resp,HttpStatus.CREATED);
	}
	
	@PostMapping("/updateCategory/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable("catId") Integer catId){
		CategoryDto resp=this.categoryService.updateCategory(dto, catId);
		return  ResponseEntity.ok(resp);
	}
	
	@GetMapping("/getCategory/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId){
		CategoryDto cat=this.categoryService.getCategory(catId);
		return ResponseEntity.ok(cat);
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> resp=this.categoryService.getCategories();
		return ResponseEntity.ok(resp);
		
	}
	
	@DeleteMapping("/deleteCategory/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity(new ApiResponse("Category deleted succesfully",true),HttpStatus.OK);
	}

}
