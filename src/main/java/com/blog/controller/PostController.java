package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entities.Post;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.AppConstents;
import com.blog.payloads.FileResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@RestController 
@RequestMapping("/api/")
public class PostController {
	@Autowired 
	private  PostService postService ;
	@Autowired
	private FileService fileService ;
	
	@Value("${project.image}")
	private String path;

	
	//create post handler 
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			@PathVariable  Integer userId,@PathVariable Integer categoryId){
		
		         PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		        
		return new ResponseEntity<PostDto>(createPost ,HttpStatus.CREATED);
	}
	//get by User 
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
	                  List<PostDto> posts = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get post by Category ;
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId){
	                  List<PostDto> posts = this.postService.getPostByCategory(catId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get AllPost **
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(// Taking the dynamic value of the pages ;
			@RequestParam(value="pageNumber" ,defaultValue =AppConstents.PAGE_NUMBER , required =  false) Integer pageNumber,
			@RequestParam(value =  "pageSize" ,defaultValue =AppConstents.PAGE_SIZE ,required = false) Integer pageSize ,
			@RequestParam(value = "sortBy" ,defaultValue =AppConstents.SORT_BY,required =false)  String sortBy 
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	//get a single post 
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
	                PostDto postDto= this.postService.getPostById(postId);
		return new  ResponseEntity<PostDto>(postDto , HttpStatus.OK) ;
		
		
	}
	//delete
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("This Post is Successfully Deleted.." ,true);
	}
	//update Post 
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost ,HttpStatus.OK);
		
	}
	
	//implementing the search 
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		 List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		
		
		return new ResponseEntity<List<PostDto>>(searchPosts ,HttpStatus.OK);
		}
	
	//uploading file image 
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
		
		String fileName = null;

			fileName = this.fileService.uploadImage(path, image);
			
			PostDto postDto = this.postService.getPostById(postId);
			
			postDto.setImageName(fileName);
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			
			return new ResponseEntity<PostDto>(updatePost ,HttpStatus.OK);
			}

	//serve image 
	
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

	
	
	
	
}
