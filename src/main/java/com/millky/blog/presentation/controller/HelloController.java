package com.millky.blog.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.millky.blog.domain.model.entity.Hello;
import com.millky.blog.infrastructure.dao.HelloDao;

@Controller
public class HelloController {

	@Autowired
	private HelloDao helloDao;

	@RequestMapping("/add")
	public String add(Hello hello) {

		helloDao.save(hello);

		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String list(Model model) {

		List<Hello> l = helloDao.findAll();
		int index = l.size();
		System.out.println(index);
		model.addAttribute("name", l.get(index - 1).getName());

		return "hello";
	}
}
