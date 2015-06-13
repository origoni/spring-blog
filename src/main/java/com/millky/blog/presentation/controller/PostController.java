package com.millky.blog.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.infrastructure.dao.PostDao;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostDao postDao;

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Post post) {
		return "form";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		post.setRegDate(LocalDateTime.now());
		return "redirect:/post/" + postDao.save(post).getId();
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<Post> postList = postDao.findAll();
		model.addAttribute("postList", postList);
		return "list";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		return "post";
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		postDao.delete(id);
		return "redirect:/post/list";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		return "form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		return "redirect:/post/" + postDao.save(post).getId();
	}
}
