package com.millky.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.blog.domain.model.entity.Hello;

public interface HelloDao extends JpaRepository<Hello, Integer> {

}
