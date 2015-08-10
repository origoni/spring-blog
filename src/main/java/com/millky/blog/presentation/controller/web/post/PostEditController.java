package com.millky.blog.presentation.controller.web.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.millky.blog.domain.model.UserSession;
import com.millky.blog.domain.model.command.PostCommand;
import com.millky.blog.domain.repository.CategoryRepository;
import com.millky.blog.domain.repository.PostRepository;
import com.millky.blog.domain.service.PostService;

@Controller
@RequestMapping("/post")
public class PostEditController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id, UserSession user) {

		model.addAttribute("postCommand", new PostCommand(postRepository.findByIdAndUser(id, user)));
		model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

		return "post/form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid PostCommand post, BindingResult bindingResult, UserSession user, Model model) {

		model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		return "redirect:/post/" + postService.editPost(post, user).getId();
	}
}
