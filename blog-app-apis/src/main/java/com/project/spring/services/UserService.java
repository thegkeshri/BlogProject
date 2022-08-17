package com.project.spring.services;

import java.util.List;

import com.project.spring.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
}
