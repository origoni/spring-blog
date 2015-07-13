package com.millky.blog.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.millky.blog.domain.model.entity.Category;
import com.millky.blog.domain.model.entity.Post;
import com.millky.blog.infrastructure.dao.CategoryDao;
import com.millky.blog.infrastructure.dao.PostDao;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostDao postDao;

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Post post, Model model) {

		List<Category> categoryList = categoryDao.findAll();

		log.debug("categoryList = {}", categoryList);

		model.addAttribute("categoryMap", categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getName)));

		return "post/form";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid Post post, BindingResult bindingResult, UserSession user) {

		log.debug("user = {}", user);

		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		post.setRegDate(LocalDateTime.now());
		post.setUserId(user.getProviderUserId());
		post.setName(user.getDisplayName());
		return "redirect:/post/" + postDao.save(post).getId();
	}

	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(value = "category", required = false, defaultValue = "0") int categoryId,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 5) Pageable pageable) {

		Page<Post> postPage;

		if (categoryId > 0) {
			postPage = postDao.findByCategoryId(categoryId, pageable);

		} else {
			postPage = postDao.findAll(pageable);
		}

		model.addAttribute("categoryId", categoryId);
		model.addAttribute("postPage", postPage);

		return "post/list";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {

		Post post = postDao.findOne(id);
		model.addAttribute("post", post);

		return "post/post";
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id, UserSession user) {

		Post post = postDao.findOne(id);
		if (post.getUserId().equals(user.getProviderUserId())) {
			postDao.delete(id);
		}

		return "redirect:/post/list";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id, UserSession user) {

		Post post = postDao.findOne(id);
		if (post.getUserId().equals(user.getProviderUserId())) {
			
			List<Category> categoryList = categoryDao.findAll();

			log.debug("categoryList = {}", categoryList);

			model.addAttribute("categoryMap", categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getName)));
			
			model.addAttribute("post", post);
		}

		return "post/form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Post post, BindingResult bindingResult, UserSession user) {

		if (bindingResult.hasErrors()) {
			return "post/form";
		}

		Post oldPost = postDao.findOne(post.getId());
		if (oldPost.getUserId().equals(user.getProviderUserId())) {
			oldPost.setTitle(post.getTitle());
			oldPost.setSubtitle(post.getSubtitle());
			oldPost.setContent(post.getContent());
			oldPost.setCategoryId(post.getCategoryId());
			return "redirect:/post/" + postDao.save(oldPost).getId();
		}

		return "post/form";
	}
}
