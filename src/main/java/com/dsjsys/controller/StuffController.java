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
import com.dsjsys.pojo.Role;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.RoleService;
import com.dsjsys.service.StuffService;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.Status;

@Controller
@Transactional
@RequestMapping("/admin/stuff")
public class StuffController {

	@Resource
	private StuffService stuffServiceImpl;
	
	@Resource
	private DeptmentService deptmentServiceImpl;
	
	@Resource
	private RoleService roleServiceImpl;
	
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="查看员工信息")
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest req,Integer currentPage,Integer pageSize) throws Exception{
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Stuff> stuffPager = stuffServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		for(int i=0;i<stuffPager.getDataList().size();i++){
			long deptId = stuffPager.getDataList().get(i).getDeptId();
			Object deptment = deptmentServiceImpl.fetch(deptId);
			Role roleTemp = roleServiceImpl.fetch(stuffPager.getDataList().get(i).getRoleId());
			stuffPager.getDataList().get(i).setDeptment((Deptment)deptment);
			stuffPager.getDataList().get(i).setRole(roleTemp);
		}
		req.setAttribute("stuffPager", stuffPager);
		return "admin/stuff/stuffinfo_list";
	}
	
/*	@RequestMapping(params="byName",method=RequestMethod.POST)
	public String byName(HttpServletRequest res,String name){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Stuff> stuffList = stuffServiceImpl.queryPage(condition, 1, 5,null,null);
		System.out.println(stuffList.getDataList().get(1));
		res.setAttribute("stuffList", stuffList);
		return null;
		//return "admin/stuff/stuffinfo_list";
	}
	*/
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="增加新员工")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Stuff stuff){
		stuffServiceImpl.save(stuff);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level3)
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest req){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		req.setAttribute("deptmentList", deptmentList);
		return "admin/stuff/stuffinfo_add";
	}
	
	
	@AuthLevel(level=AuthConfig.level3)
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest req,Long id){
		Stuff stuff = stuffServiceImpl.fetch(id);
		List<Role> roleList = roleServiceImpl.findAll();
		req.setAttribute("roleList", roleList);
		req.setAttribute("stuff", stuff);
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		req.setAttribute("deptmentList", deptmentList);
		return "admin/stuff/stuffinfo_update";
	}
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="更新员工信息")
	@RequestMapping(value="saveUpdate",method=RequestMethod.POST)
	public String saveUpdate(HttpServletRequest req,Stuff stuff){
		stuffServiceImpl.update(stuff);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="删除员工")
	@RequestMapping(value="del",method=RequestMethod.GET)
	public String delete(Stuff stuff){
		stuffServiceImpl.deleteById(stuff.getId());
		return "success/success";
	}
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="启用该员工信息")
	@RequestMapping(value="stop",method=RequestMethod.GET)
	public String stop(Long id){
		Stuff stuff = new Stuff();
		stuff.setId(id);
		stuff.setLocked(1);
		stuffServiceImpl.update(stuff);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="禁用该员工信息")
	@RequestMapping(value="start",method=RequestMethod.GET)
	public String start(Long id){
		Stuff stuff = new Stuff();
		stuff.setId(id);
		stuff.setLocked(0);
		stuffServiceImpl.update(stuff);
		return "success/success";
	}
	@AuthLevel(level=AuthConfig.level1)
	@ResponseBody
	@RequestMapping(value="stuffinfo",method=RequestMethod.POST)
	public JsonMessage stuffInfo(){
		List<Stuff> stuffList = stuffServiceImpl.findAll();
		JsonMessage jsonMessage = new JsonMessage(stuffList,"","",Status.SUCCESS);
		System.out.println(stuffList.get(0).getName());
		return jsonMessage;
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@ResponseBody
	@RequestMapping(value="stuffList",method=RequestMethod.GET)
	public JsonMessage stuffList(Long deptId){
		List<Stuff> stuffList = stuffServiceImpl.findList("deptId", deptId);
		JsonMessage jsonMessage = new JsonMessage(stuffList,"","",Status.SUCCESS);
		return jsonMessage;
	}

}