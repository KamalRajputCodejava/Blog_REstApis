package com.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService; 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		
		
		return  new ResponseEntity<>(createUserDto,HttpStatus.CREATED) ;
	}
	
	// Put api for updating the user ;   //@RequestBody is use for the read the data which comes in the json form ; and bind with the persnet vaariable in the controller method ;
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);

		return ResponseEntity.ok(updatedUser);
	}
	
	//delete api  ;;
	@PreAuthorize("hasRole('ADMIN')")      //this api is access by admin only not for normal users 
	@DeleteMapping("/{userId}")
	 public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		          this.userService.deleteUser(userId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("user Deleted Succesfully.",true),HttpStatus.OK); //int the ok method we can return the map also for showing the message ;
		
	 }
	
	// Get Multiple users ;
	@GetMapping("/")
	public  ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers()); 
	}
	
	// get single user  ;
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
		
		
		
		return ResponseEntity.ok(this.userService.getUserById(userId)); 
		
	}
	
	
 	
	
	
	
	
	
	
	
	
	
	
	
	

}
