package com.project.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring.entities.Category;
import com.project.spring.entities.Post;
import com.project.spring.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	//Page<Post> findByCategoryId(Integer catId, Pageable p);
}
