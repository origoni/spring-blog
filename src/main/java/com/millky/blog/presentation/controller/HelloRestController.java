package com.millky.blog.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.millky.blog.domain.model.entity.Hello;
import com.millky.blog.infrastructure.dao.HelloDao;

@RestController
public class HelloRestController {

	@Autowired
	private HelloDao helloDao;

	@RequestMapping("/add")
	public Hello add(Hello hello) {

		Hello helloData = helloDao.save(hello);

		return helloData;
	}

	@RequestMapping("/list")
	public List<Hello> list(Model model) {

		List<Hello> helloList = helloDao.findAll();

		return helloList;
	}

	@RequestMapping("/test")
	public String index() {
		return "helloworld!";
	}
}