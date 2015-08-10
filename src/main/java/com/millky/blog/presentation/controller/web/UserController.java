package com.millky.blog.presentation.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		return "connect/login";
	}
}
