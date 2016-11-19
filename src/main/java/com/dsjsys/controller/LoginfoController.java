package com.dsjsys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dsjsys.annotation.AuthLevel;
import com.dsjsys.annotation.SystemControllerLog;
import com.dsjsys.config.AuthConfig;
import com.dsjsys.pojo.Loginfo;
import com.dsjsys.pojo.Loginfo;
import com.dsjsys.service.LoginfoService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Controller
@Transactional
@RequestMapping("/admin/system")
public class LoginfoController {

	@Resource
	private LoginfoService loginfoServiceImpl;
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="/loginfo/list",method=RequestMethod.GET)
	public String list(HttpServletRequest res,Integer currentPage,Integer pageSize){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Loginfo> loginfoPager = loginfoServiceImpl.queryPage(condition, currentPage, pageSize,"date","DESC");
		res.setAttribute("loginfoPager", loginfoPager);
		return "admin/system/log/loginfo_list";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="/loginfo/del",method=RequestMethod.GET)
	public String delete(Loginfo loginfo){
		loginfoServiceImpl.deleteById(loginfo.getId());
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="/loginfo/delAll",method=RequestMethod.POST)
	public String delAll(){
		List<Loginfo> loginfoList = loginfoServiceImpl.findAll();
		for(int i = 0;i<loginfoList.size();i++){
			loginfoServiceImpl.deleteById(loginfoList.get(i).getId());
		}
		
		return "success/success";
	}
}
