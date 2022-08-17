package com.project.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
