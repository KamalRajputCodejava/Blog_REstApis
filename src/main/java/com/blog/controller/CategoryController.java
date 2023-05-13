package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto) {
		CategoryDto createdCategory = this.categoryService.createCategory(catDto);

		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}

	// updateCategory

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCcategory(@Valid @PathVariable("catId") Integer catId,
			@RequestBody CategoryDto catDto) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(catDto, catId);

		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	// deleteCategory

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCcategory(@PathVariable("catId") Integer catId) {
		this.categoryService.deleteCategory(catId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("category is Deleted.", true), HttpStatus.OK);

	}

	// get the single category ;
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId) {
		CategoryDto categoryDto = this.categoryService.getCategory(catId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}

	// get all categories ;
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getcategories() {

		List<CategoryDto> categories = this.categoryService.getCategories();

		return ResponseEntity.ok(categories); // this is the bodyBuilderMethod ;

	}

}
