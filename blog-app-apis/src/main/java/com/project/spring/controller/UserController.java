package com.project.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.payloads.ApiResponse;
import com.project.spring.payloads.UserDto;
import com.project.spring.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto dtos=this.userService.createUser(userDto);
		return new ResponseEntity<>(dtos, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId ){
	
	UserDto dto=this.userService.updateUser(userDto, uId);
	return   ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userId") Integer uId){
		 this.userService.deleteUser(uId);
		return new ResponseEntity(new ApiResponse("User Deleted Succesfully",true),HttpStatus.OK);
	}
		
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
	
		return  ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
}
