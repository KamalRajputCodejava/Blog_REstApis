package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {
	//create category  
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	//update category ;
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete category ; 
	 
	public void deleteCategory(Integer categoryId);
	 
	//get Category  ;
	
	public CategoryDto getCategory(Integer categoryId);
	
	//get all  category ; 
	
	public List<CategoryDto> getCategories();
	
	

}
