package com.project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.payloads.ApiResponse;
import com.project.spring.payloads.PostDto;
import com.project.spring.payloads.PostResponse;
import com.project.spring.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{catId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto,
			@PathVariable Integer userId,
			@PathVariable Integer catId){
		
		PostDto post=this.postService.createPost(dto, userId, catId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer userId){
		List<PostDto> posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("catId") Integer catId){
		List<PostDto> posts=this.postService.getPostByCategory(catId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue="0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize, 
			@RequestParam(value="sortBy", defaultValue="postId",required=false) String sortBy,
			@RequestParam(value ="sortDir", defaultValue="ASC", required = false) String sortDir){
		return ResponseEntity.ok(this.postService.getAllPost(pageNumber,pageSize,sortBy, sortDir));
	}
	
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity(new ApiResponse("Post deleted Succesfully",true),HttpStatus.OK);
	}
	
	@PostMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable Integer postId){
		PostDto updatedPost=this.postService.updatePost(dto, postId);
		return ResponseEntity.ok(updatedPost);
	}

}
