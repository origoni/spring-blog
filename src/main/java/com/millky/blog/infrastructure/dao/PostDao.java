package com.millky.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.blog.domain.model.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer> {

}
