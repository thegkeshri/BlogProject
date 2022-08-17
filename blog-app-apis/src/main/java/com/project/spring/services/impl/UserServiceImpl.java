package com.project.spring.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.payloads.UserDto;
import com.project.spring.repositories.UserRepo;
import com.project.spring.services.UserService;
import com.project.spring.entities.*;
import com.project.spring.exceptions.ResourceNotFound;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user=this.userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFound("User", "Id", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("User", "Id", id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list=this.userRepo.findAll();
		List <UserDto> userDtos=list.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFound("User", "ID", id)); 
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	

}
