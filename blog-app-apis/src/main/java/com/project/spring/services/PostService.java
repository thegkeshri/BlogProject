package com.project.spring.services;

import java.util.List;

import com.project.spring.entities.Post;
import com.project.spring.payloads.PostDto;
import com.project.spring.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto dto,Integer userId, Integer catId);
	PostDto updatePost(PostDto dto, Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer catId);
	List<PostDto> getPostByUser(Integer userId);
	List<Post> searchPost(String keyword);

}
