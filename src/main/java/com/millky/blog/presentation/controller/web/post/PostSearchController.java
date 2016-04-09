package com.millky.blog.presentation.controller.web.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.domain.service.PostSearchService;
import com.millky.blog.domain.service.factory.SearchServiceFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PostSearchController {

	@Autowired
	private SearchServiceFactory searchServiceFactory;

	private PostSearchService searchService;

	@RequestMapping(value = "/post/search")
	public String view(Model model, @RequestParam(defaultValue = "title") String type,
			@RequestParam(required = true) String query,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 5) Pageable pageable) {

		log.info("type = {} & query = {}", type, query);

		searchService = searchServiceFactory.getSearchService(type);

		Page<Post> postPage = searchService.findPost(query, pageable);

		model.addAttribute("type", type);
		model.addAttribute("query", query);
		model.addAttribute("postPage", postPage);

		return "post/list";
	}
}
