package com.millky.blog.infrastructure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.entity.PostTag;

public interface PostTagDao extends JpaRepository<PostTag, Integer> {

	List<PostTag> findByTagName(String tagName);

	List<PostTag> findByPost(Post post);
}
