package com.project.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{


}
