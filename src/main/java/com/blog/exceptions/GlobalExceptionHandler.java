package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

@RestControllerAdvice   //this annotation makes this class as global exception handler class which advice all present controllers;
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class) //when we add this class in the annotation we can get the object of this class for the further use 
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message =ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>( apiResponse ,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String,String> resp = new  HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
	//important   
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
			
		});
		
		return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class) //when we add this class in the annotation we can get the object of this class for the further use 
	public ResponseEntity<ApiResponse> apiExceptionHandler(ApiException ex){
		String message =ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,true);
		
		return new ResponseEntity<ApiResponse>( apiResponse ,HttpStatus.BAD_REQUEST);
		
		
	}
	
}
