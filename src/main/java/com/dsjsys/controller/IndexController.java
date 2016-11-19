package com.dsjsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "login";
	}
}
