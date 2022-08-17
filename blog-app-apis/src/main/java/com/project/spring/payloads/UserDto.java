package com.project.spring.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	 
	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message="Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10, message="Password must be minimum of 3 characters or maximum of 10 characters!")
	private String password;
	
	@NotEmpty
	@Size(min=10, message="About must be minimum of 10 characters!!")
	private String about;
	
	
}
