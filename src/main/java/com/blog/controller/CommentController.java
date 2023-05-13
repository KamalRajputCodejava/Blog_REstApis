package com.blog.controller;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired 
	private CommentService commentService ;
	
    @PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createCommnet(@RequestBody CommentDto commentDto, @PathVariable Integer posId){
		 
		 CommentDto createComm = this.commentService.createComment(commentDto, posId);
		
		return new ResponseEntity<CommentDto>(createComm,HttpStatus.CREATED) ; 
	}
    @PostMapping("/post/comments/{commentId}")
    public ResponseEntity<ApiResponse> createCommnet(@PathVariable Integer commentId){
    	
    	this.commentService.deleteComment(commentId);
    	
    	return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully..",true),HttpStatus.OK) ; 
    }
    
    
	
}
