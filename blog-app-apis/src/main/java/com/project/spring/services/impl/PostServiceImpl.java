package com.project.spring.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.spring.entities.Category;
import com.project.spring.entities.Post;
import com.project.spring.entities.User;
import com.project.spring.exceptions.ResourceNotFound;
import com.project.spring.payloads.PostDto;
import com.project.spring.payloads.PostResponse;
import com.project.spring.repositories.CategoryRepo;
import com.project.spring.repositories.PostRepo;
import com.project.spring.repositories.UserRepo;
import com.project.spring.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto dto,Integer userId,Integer catId) {
		 User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "USer Id", userId));
		Category cat=this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFound("Category", "Category Id", catId));

		Post post=this.modelMapper.map(dto, Post.class);
		post.setImgName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post", "PostId", postId));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImgName(dto.getImgName());
		
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		} else
			sort=Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePosts=this.postRepo.findAll(p);
		List<Post> posts=pagePosts.getContent();
		List<PostDto> dtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResp=new PostResponse();
		postResp.setContent(dtos);
		postResp.setPageNumber(pagePosts.getNumber());
		postResp.setPageSize(pagePosts.getSize());
		postResp.setTotalElements(pagePosts.getTotalElements());
		postResp.setTotalPages(pagePosts.getTotalPages());
		postResp.setLastpage(pagePosts.isLast());
		
		return postResp;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getPostByCategory(Integer catId) {
		Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFound("Category","category id", catId));
		//Pageable p=PageRequest.of(pageNumber, pageSize);
		List<Post> posts=this.postRepo.findByCategory(cat);
		
		//List<Post> posts=pagePosts.getContent();
		
		List<PostDto> dtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		//PostResponse postResp=new PostResponse();
		/*
		 * postResp.setContent(dtos); postResp.setPageNumber(pagePosts.getNumber());
		 * postResp.setPageSize(pagePosts.getSize());
		 * postResp.setTotalElements(pagePosts.getTotalElements());
		 * postResp.setTotalPages(pagePosts.getTotalPages());
		 * postResp.setLastpage(pagePosts.isLast());
		 */
		return dtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> dtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
