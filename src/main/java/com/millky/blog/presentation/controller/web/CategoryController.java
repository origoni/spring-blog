package com.millky.blog.presentation.controller.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.millky.blog.domain.model.entity.Category;
import com.millky.blog.infrastructure.dao.CategoryDao;

@Controller
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;

	@ResponseBody
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public Category add(@RequestParam(value = "categoryName", required = true) String categoryName) {

		Category category = new Category();
		category.setName(categoryName);
		category.setRegDate(new Date());

		return categoryDao.save(category);
	}
}
