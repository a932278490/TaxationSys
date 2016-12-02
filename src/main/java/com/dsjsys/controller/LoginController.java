package com.dsjsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsjsys.annotation.SystemControllerLog;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.pojo.Role;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.RoleService;
import com.dsjsys.service.StuffService;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.Status;

@Controller
@Transactional
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@Resource
	private RoleService roleServiceImpl;
	
	@Resource
	private DeptmentService deptmentServiceImpl;
	
	@RequestMapping("/index")
	public String index(){
		return "login";
	}
	
	@RequestMapping("/refresh")
	public String refresh(){
		return "error/refresh";
	}
	
	@SystemControllerLog(description="员工登陆")
	@ResponseBody
	@RequestMapping(value="login.do",params="account",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonMessage login(HttpServletRequest request,String account,String password,Model model){
		HttpSession session = null;
		JsonMessage jsonMessage = null;
		Stuff stuff = stuffServiceImpl.findOne("phone", account);
		if(stuff==null || !stuff.getPhone().equals(account) || !stuff.getPassword().equals(password) || stuff.getLocked().equals(1)) {
			jsonMessage = new JsonMessage("","手机号或者密码错误或该用户已被禁用","",Status.ERROR);
			return jsonMessage;
		}else if(stuff!=null){
			session =request.getSession();
			Role role = roleServiceImpl.fetch(stuff.getRoleId());
			stuff.setRole(role);
			Deptment deptment = deptmentServiceImpl.fetch(stuff.getDeptId());
			stuff.setDeptment(deptment);
			session.setAttribute("loginStuff", stuff);
			session.setAttribute("messageAdvice","true");
		}
		jsonMessage = new JsonMessage("","","admin/index",Status.SUCCESS);
		return jsonMessage;
	}

}
