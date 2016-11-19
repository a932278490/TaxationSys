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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsjsys.annotation.AuthLevel;
import com.dsjsys.annotation.SystemControllerLog;
import com.dsjsys.config.AuthConfig;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.StuffService;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.Status;

@Controller
@Transactional
@RequestMapping("/admin/deptment")
public class DeptmentController {

	@Resource
	private DeptmentService deptmentServiceImpl;
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@AuthLevel(level=AuthConfig.level1)
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest res,Integer currentPage,Integer pageSize){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Deptment> deptmentPager = deptmentServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		res.setAttribute("deptmentPager", deptmentPager);
		return "admin/deptment/deptmentinfo_list";
	}
	
/*	@RequestMapping(params="byName",method=RequestMethod.POST)
	public String byName(HttpServletRequest res,String name){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Deptment> deptmentList = deptmentServiceImpl.queryPage(condition, 1, 5,null,null);
		System.out.println(deptmentList.getDataList().get(1));
		res.setAttribute("deptmentList", deptmentList);
		return null;
		//return "admin/deptment/deptmentinfo_list";
	}
	*/
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="增加新部门")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Deptment deptment){
		
		deptmentServiceImpl.save(deptment);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest req){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		List<Stuff> stuffList = stuffServiceImpl.findAll();
		req.setAttribute("stuffList", stuffList);
		req.setAttribute("deptmentList", deptmentList);
		return "admin/deptment/deptmentinfo_add";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest req,Long id){
		Deptment deptment = deptmentServiceImpl.fetch(id);
		List<Stuff> stuffList = stuffServiceImpl.findAll();
		req.setAttribute("stuffList", stuffList);
		req.setAttribute("deptment", deptment);
		return "admin/deptment/deptmentinfo_update";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="更新部门信息")
	@RequestMapping(value="saveUpdate",method=RequestMethod.POST)
	public String saveUpdate(HttpServletRequest req,Deptment deptment){
		deptmentServiceImpl.update(deptment);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="删除部门信息")
	@RequestMapping(value="del",method=RequestMethod.GET)
	public String delete(Deptment deptment){
		deptmentServiceImpl.deleteById(deptment.getId());
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@ResponseBody
	@RequestMapping(value="deptmentinfo",method=RequestMethod.POST)
	public JsonMessage deptmentInfo(){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		JsonMessage jsonMessage = new JsonMessage(deptmentList,"","",Status.SUCCESS);
		System.out.println(deptmentList.get(0).getName());
		return jsonMessage;
	}

}