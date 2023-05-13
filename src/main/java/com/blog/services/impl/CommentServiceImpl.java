package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comments;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired 
	private PostRepo postRepo ;
	@Autowired
	private CommentRepo commentRepo ;
	@Autowired
	private ModelMapper modelMapper ;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","postId",postId));
		             Comments comment = this.modelMapper.map(commentDto, Comments.class);
		             comment.setPost(post);
		             Comments saveComment = this.commentRepo.save(comment);
		           
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer comId) {
	      Comments com = this.commentRepo.findById(comId).orElseThrow(()->new ResourceNotFoundException("Comment ","CommentId",comId));
		
	}

}
