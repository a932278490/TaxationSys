package com.dsjsys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsjsys.service.StuffService;
import com.dsjsys.annotation.SystemControllerLog;


@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@SystemControllerLog(description="访问后台首页")
	@RequestMapping("/main")
	public String main(){
		return "admin/index/main";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "admin/index/index";
	}
	
	@RequestMapping("/top")
	public String top(){
		return "admin/index/top";
	}
	
	@RequestMapping("/left")
	public String left(){
		return "admin/index/left";
	}
	
	@RequestMapping("/bootm")
	public String bootm(HttpServletRequest res,Integer currentPage,Integer pageSize){
		return "admin/index/bootm";
	}
	@SystemControllerLog(description="退出系统")
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		System.out.println("这里是 test控制器");
		System.out.println(request.getRequestURI());
		return "admin/index/index";
	}
}
