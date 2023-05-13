package com.blog.services;

import java.util.List;

import com.blog.payloads.CommentDto;

public interface CommentService {

	 //get comment 
	 //get Comment by user 
	 //create comment 
	  CommentDto  createComment(CommentDto commentDto ,Integer postId );
	 // Update Comment 
	// CommentDto  updateCommment(CommentDto commentDto ,Integer userId ,Integer categoryId);
	
    //Delete Comment ; 
	 void  deleteComment(Integer comId);
	
}
