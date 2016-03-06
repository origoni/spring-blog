package com.millky.blog.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.millky.blog.domain.model.entity.PostTag;
import com.millky.blog.infrastructure.dao.PostTagDao;

@Repository
public class PostTagRepository {

	@Autowired
	PostTagDao postTagDao;

	@Autowired
	TagRepository tagRepository;

	public void insertPostTag(PostTag postTag) {
		postTag.setRegDate(new Date());
		postTagDao.save(postTag);

		tagRepository.increaseUseCount(postTag.getTagId());
	}

	public List<PostTag> findByPostId(int postId) {
		return postTagDao.findByPostId(postId);
	}

	public void deletePostTag(PostTag postTag) {
		postTagDao.delete(postTag.getId());

		tagRepository.decreaseUseCount(postTag.getTagId());
	}
}