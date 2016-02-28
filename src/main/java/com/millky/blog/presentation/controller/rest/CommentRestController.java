package com.millky.blog.presentation.controller.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.entity.Comment;
import com.millky.blog.domain.model.exception.IllegalUserException;
import com.millky.blog.infrastructure.dao.CommentDao;

@RestController
public class CommentRestController {

	@Autowired
	CommentDao commentDao;

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public List<Comment> list(@RequestParam(value = "postId", required = true) int postId) {
		return commentDao.findByPostId(postId);
	}

	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Comment save(@RequestParam(value = "postId", required = true) int postId,
			@RequestParam(value = "content", required = true) String content, UserSession user) {

		// 빠르게 테스트하기위해 이렇게 만들었지만, 글에 권한이 있거나 하면 훨씬 복잡해짐.
		Comment comment = new Comment();
		comment.setPostId(postId);
		comment.setContent(content);
		comment.setUserId(user.getProviderUserId());
		comment.setName(user.getDisplayName());
		comment.setRegDate(new Date());

		return commentDao.save(comment);
	}

	@RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestParam(value = "postId", required = true) int postId, @PathVariable int id,
			UserSession user) {
		
		// 확인해야 할 것이 많지만.. 최소 같은작성자인지는 확인하자.
		Comment comment = commentDao.findOne(id);
		if (comment.getUserId().equals(user.getProviderUserId())) {
			commentDao.delete(id);
		} else {
			throw new IllegalUserException("Not the Writer.");
		}
	}
}
