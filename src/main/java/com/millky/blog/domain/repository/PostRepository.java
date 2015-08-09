package com.millky.blog.domain.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.command.PostCommand;
import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.model.exception.IllegalUserException;
import com.millky.blog.infrastructure.dao.PostDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PostRepository {

	@Autowired
	private PostDao postDao;

	public Page<Post> getPostList(Pageable pageable) {
		Page<Post> postPage = postDao.findAll(pageable);
		log.debug("postPage = {}", postPage);

		return postPage;
	}

	public Page<Post> getPostList(Pageable pageable, int categoryId) {
		return postDao.findByCategoryId(categoryId, pageable);
	}

	public Page<Post> getPostList(Pageable pageable, String tagName) {
		return postDao.findByPostTagListTagName(tagName, pageable);
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
		post.setUpdateDate(new Date());

		return postDao.save(post);
	}

	public void deletePost(int id) throws IllegalUserException, IllegalArgumentException {

		postDao.delete(id);
	}

	public Post editPost(PostCommand postCommand) {
		Post post = getPostById(postCommand.getId());

		post.setUpdateDate(new Date());
		post.setTitle(postCommand.getTitle());
		post.setSubtitle(postCommand.getSubtitle());
		post.setContent(postCommand.getContent());
		post.setCategoryId(postCommand.getCategoryId());

		return post;
	}

	public Post findByIdAndUser(int id, UserSession user) throws RuntimeException {
		if (isThisUserPostWriter(user, id))
			return getPostById(id);
		else
			throw new IllegalUserException("Not the Writer.");
	}

	public boolean isThisUserPostWriter(UserSession user, int id) throws IllegalArgumentException {
		Post post = getPostById(id);

		return post.getUserId().equals(user.getProviderUserId());
	}

}
