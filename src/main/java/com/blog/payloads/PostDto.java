package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {
	private Integer postId ;
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	private String  postTitle;
	
	private String  content ; 
	
	private String imageName ;
	
	private Date addedDate ;
	
	
	private CategoryDto category ;
	private UserDto   user ;
	private Set<CommentDto> comments = new HashSet<>();
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public PostDto(String postTitle, String content, String imageName, Date addedDate, CategoryDto category,
			UserDto user) {
		super();
		this.postTitle = postTitle;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
