package com.millky.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.blog.domain.model.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
