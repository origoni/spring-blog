package com.millky.blog.presentation.controller.web.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.service.PostService;

@Controller
@RequestMapping("/post")
public class PostDeleteController {

	@Autowired
	private PostService postService;

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id, UserSession user) {

		postService.deletePost(id, user);

		return "redirect:/post/list";
	}
}
