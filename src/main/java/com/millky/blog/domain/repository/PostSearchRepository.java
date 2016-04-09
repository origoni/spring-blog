package com.millky.blog.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.infrastructure.dao.PostDao;

@Repository
public class PostSearchRepository {

	@Autowired
	private PostDao postDao;

	public Page<Post> findPostByTitle(String query, Pageable pageable) {

		return postDao.findByTitleContainingOrSubtitleContaining(query, query, pageable);
	}

	public Page<Post> findPostByContent(String query, Pageable pageable) {

		return postDao.findByContentContaining(query, pageable);
	}

	public Page<Post> findPostByTitleAndContent(String query, Pageable pageable) {

		return postDao.findByTitleContainingOrSubtitleContainingOrContentContaining(query, query,
				HtmlUtils.htmlEscape(query), pageable);
	}
}
