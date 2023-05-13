package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	//post create 
    PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId);
	
	//post update 
	PostDto updatePost(PostDto postDto ,Integer postId );
	//post  Delete 
	void deletePost(Integer postId);
	
	//post get Single 
	 
	PostDto getPostById(Integer postId);
	
	
	//get Multiple post 
	
	PostResponse getAllPost(Integer pageNumber , Integer pageSize ,String sortBy);
	
    //post get By  category  ;
	 List<PostDto> getPostByCategory(Integer categoryId);
	
	//post get by user  ;
	 List<PostDto> getPostByUser(Integer userId);
	 
	 //get all by search Post 
	 
	 List<PostDto> searchPosts(String Keyword);
}
