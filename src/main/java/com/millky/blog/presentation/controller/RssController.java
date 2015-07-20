package com.millky.blog.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.millky.blog.domain.repository.PostRepository;

@Controller
@RequestMapping("/rss")
public class RssController {

	@Autowired
	private PostRepository postRepository;

	@RequestMapping("/post/list")
	public String list(Model model,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 10) Pageable pageable) {

		model.addAttribute("postList", postRepository.getPostList(pageable, 0).getContent());

		return "post/rss";
	}
}
