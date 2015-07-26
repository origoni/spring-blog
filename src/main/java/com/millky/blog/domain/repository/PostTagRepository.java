package com.millky.blog.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.entity.PostTag;
import com.millky.blog.infrastructure.dao.PostTagDao;

@Repository
public class PostTagRepository {

	@Autowired
	PostTagDao postTagDao;

	public void insertPostTag(PostTag postTag) {
		postTag.setRegDate(new Date());
		postTagDao.save(postTag);
	}

	public List<PostTag> findByPost(Post post) {
		return postTagDao.findByPost(post);
	}
}