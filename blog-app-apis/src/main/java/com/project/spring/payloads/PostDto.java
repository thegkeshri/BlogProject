package com.project.spring.payloads;

import java.util.Date;

import com.project.spring.entities.Category;
import com.project.spring.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String postId;
	private String title;
	private String content;
	private String imgName="default.png";
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	

}
