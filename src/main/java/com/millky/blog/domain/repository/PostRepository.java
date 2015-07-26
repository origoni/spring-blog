package com.millky.blog.domain.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.exception.IllegalUserException;
import com.millky.blog.infrastructure.dao.PostDao;

@Repository
public class PostRepository {

	@Autowired
	private PostDao postDao;

	public Page<Post> getPostList(Pageable pageable, int categoryId) {
		Page<Post> postPage;

		if (categoryId > 0) {
			postPage = postDao.findByCategoryId(categoryId, pageable);
		} else {
			postPage = postDao.findAll(pageable);
		}

		return postPage;
	}

	public Post getPostById(int id) throws IllegalArgumentException {

		Post post = postDao.findOne(id);

		if (post == null) {
			throw new IllegalArgumentException("Post Not Found.");
		}

		return post;
	}

	public Post writePost(Post post) {
		post.setRegDate(new Date());

		return postDao.save(post);
	}

	public void deletePost(int id, UserSession user) throws IllegalUserException, IllegalArgumentException {
		if (isThisUserPostWriter(user, id)) {
			postDao.delete(id);
		} else {
			throw new IllegalUserException("Not the Writer.");
		}
	}

	@Transactional
	public Post editPost(Post post, UserSession user) throws RuntimeException {
		Post oldPost = findByIdAndUser(post.getId(), user);

		oldPost.setTitle(post.getTitle());
		oldPost.setSubtitle(post.getSubtitle());
		oldPost.setContent(post.getContent());
		oldPost.setCategoryId(post.getCategoryId());

		return oldPost;
	}

	public Post findByIdAndUser(int id, UserSession user) throws RuntimeException {
		if (isThisUserPostWriter(user, id))
			return getPostById(id);
		else
			throw new IllegalUserException("Not the Writer.");
	}

	private boolean isThisUserPostWriter(UserSession user, int id) throws IllegalArgumentException {
		Post post = getPostById(id);

		return post.getUserId().equals(user.getProviderUserId());
	}

}
