package com.dsjsys.controller.system;

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
import com.dsjsys.pojo.Vehicle;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.RoleService;
import com.dsjsys.service.StuffService;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.Status;

@Controller
@Transactional
@RequestMapping("/admin/system/role")
public class RoleController {

	@Resource
	private RoleService roleServiceImpl;
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@Resource
	private DeptmentService deptmentServiceImpl;
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="查看角色信息")
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest res,Integer currentPage,Integer pageSize){
		
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Role> rolePager = roleServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		res.setAttribute("rolePager", rolePager);
		return "admin/system/role/roleinfo_list";
	}
	
/*	@RequestMapping(params="byName",method=RequestMethod.POST)
	public String byName(HttpServletRequest res,String name){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Role> roleList = roleServiceImpl.queryPage(condition, 1, 5,null,null);
		System.out.println(roleList.getDataList().get(1));
		res.setAttribute("roleList", roleList);
		return null;
		//return "admin/role/roleinfo_list";
	}
	*/
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="增加新角色")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Role role){
		roleServiceImpl.save(role);
		return "success/success";
	}
	
	@AuthLevel(level=2)
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest req){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		req.setAttribute("deptmentList", deptmentList);
		return "admin/system/role/roleinfo_add";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest req,Long id){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		req.setAttribute("deptmentList", deptmentList);
		Role role = roleServiceImpl.fetch(id);
		req.setAttribute("role", role);
		return "admin/system/role/roleinfo_update";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="更新角色信息")
	@RequestMapping(value="saveUpdate",method=RequestMethod.POST)
	public String saveUpdate(HttpServletRequest req,Role role){
		roleServiceImpl.update(role);
		return "success/success";
	}
	
	@AuthLevel(level=2)
	@SystemControllerLog(description="删除角色")
	@RequestMapping(value="del",method=RequestMethod.GET)
	public String delete(Role role){
		roleServiceImpl.deleteById(role.getId());
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="启用该角色信息")
	@RequestMapping(value="stop",method=RequestMethod.GET)
	public String stop(Long id){
		Role role = roleServiceImpl.fetch(id);
		role.setId(id);
		role.setLocked(1);
		roleServiceImpl.update(role);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="禁用该角色信息")
	@RequestMapping(value="start",method=RequestMethod.GET)
	public String start(Long id){
		Role role = roleServiceImpl.fetch(id);
		role.setLocked(0);
		roleServiceImpl.update(role);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@ResponseBody
	@RequestMapping(value="roleinfo",method=RequestMethod.POST)
	public JsonMessage roleInfo(){
		List<Role> roleList = roleServiceImpl.findAll();
		JsonMessage jsonMessage = new JsonMessage(roleList,"","",Status.SUCCESS);
		return jsonMessage;
	}

}