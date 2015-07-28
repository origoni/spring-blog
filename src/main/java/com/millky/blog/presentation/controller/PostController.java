package com.millky.blog.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.command.PostCommand;
import com.millky.blog.domain.repository.CategoryRepository;
import com.millky.blog.domain.repository.PostRepository;
import com.millky.blog.domain.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(PostCommand post, Model model) {

		model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

		return "post/form";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid PostCommand post, BindingResult bindingResult, UserSession user) {

		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		return "redirect:/post/" + postService.writePost(post, user).getId();
	}

	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(value = "category", required = false, defaultValue = "0") int categoryId,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 5) Pageable pageable) {

		model.addAttribute("categoryId", categoryId);
		model.addAttribute("postPage", postRepository.getPostList(pageable, categoryId));

		return "post/list";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {

		model.addAttribute("post", postRepository.getPostById(id));

		log.debug("post = {}", postRepository.getPostById(id));

		return "post/post";
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id, UserSession user) {

		postService.deletePost(id, user);

		return "redirect:/post/list";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id, UserSession user) {

		model.addAttribute("postCommand", new PostCommand(postRepository.findByIdAndUser(id, user)));
		model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

		return "post/form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid PostCommand post, BindingResult bindingResult, UserSession user) {

		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		return "redirect:/post/" + postService.editPost(post, user).getId();
	}
}
