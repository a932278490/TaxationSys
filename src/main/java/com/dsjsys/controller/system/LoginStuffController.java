package com.dsjsys.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dsjsys.pojo.Stuff;

@Controller
@RequestMapping("/admin/system/loginStuff")
public class LoginStuffController {

	@RequestMapping(value="stuffInfo",method=RequestMethod.GET)
	public String loginStuffInfo(HttpServletRequest req){
		return "admin/system/loginStuff/stuffInfo";
	}
	
}
