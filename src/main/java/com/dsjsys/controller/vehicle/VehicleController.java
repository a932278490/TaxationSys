package com.dsjsys.controller.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.dsjsys.pojo.Stuff;
import com.dsjsys.pojo.Vehicle;
import com.dsjsys.pojo.VehicleApply;
import com.dsjsys.service.MessageService;
import com.dsjsys.service.StuffService;
import com.dsjsys.service.VehicleApplyService;
import com.dsjsys.service.VehicleService;
import com.dsjsys.service.impl.StuffServiceImpl;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.MessageUtil;

@Controller
@Transactional
@RequestMapping("/admin/vehicle")
public class VehicleController {

	@Resource
	private VehicleService vehicleServiceImpl;
	
	@Resource
	private VehicleApplyService vehicleApplyServiceImpl;
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@Resource
	private MessageService messageServiceImpl;
	
	@AuthLevel(level=AuthConfig.level1)
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest req,Integer currentPage,Integer pageSize){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Vehicle> vehiclePager = vehicleServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		req.setAttribute("vehiclePager", vehiclePager);
		return "admin/vehicle/vehicleinfo_list";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest req){
		List<Stuff> driverList = stuffServiceImpl.findList("deptId", "20");
		req.setAttribute("driverList", driverList);
		return "admin/vehicle/vehicleinfo_add";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="新增车辆信息")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Vehicle vehicle){
		vehicle.setRemain(vehicle.getCapacity());
		vehicle.setStatus(1);
		vehicle.setApplyId((long) 0);
		vehicleServiceImpl.save(vehicle);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest req,Long id){
		List<Stuff> driverList = stuffServiceImpl.findList("deptId", "20");
		Vehicle vehicle = vehicleServiceImpl.fetch(id);
		req.setAttribute("vehicle", vehicle);
		req.setAttribute("driverList", driverList);
		return "admin/vehicle/vehicleinfo_update";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="更新车辆信息")
	@RequestMapping(value="saveUpdate",method=RequestMethod.POST)
	public String saveUpdate(HttpServletRequest req,Vehicle vehicle){
		vehicle.setRemain(vehicle.getCapacity());
		vehicleServiceImpl.update(vehicle);
		return "success/success";
	}
	@ResponseBody
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="派出车辆")
	@RequestMapping(value="stop",method=RequestMethod.GET)
	public JsonMessage stop(HttpServletRequest req,Long id){
		Vehicle vehicle = vehicleServiceImpl.fetch(id);
		if(vehicle.getRemain()==vehicle.getCapacity()){
			return new JsonMessage("distrubuteError");
		}
		vehicle.setStatus(0);
		vehicleServiceImpl.update(vehicle);
		
		/***************************消息发送处理，可以放在切面类中**************************************************************/
		Stuff loginStuff = (Stuff)req.getSession().getAttribute("loginStuff");
		List<Stuff> stuffList = new ArrayList<Stuff>();
		stuffList = stuffServiceImpl.findList("deptId", "20");
		
		this.messageServiceImpl.sendMessage(loginStuff, stuffList, loginStuff.getName()+"派出车辆");
		/*********************************************************************************/
		
		
		return new JsonMessage("success");
	}
	
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="车辆复位")
	@RequestMapping(value="start",method=RequestMethod.GET)
	public String start(Long id){
		Vehicle vehicle = vehicleServiceImpl.fetch(id);
		vehicle.setStatus(1);
		vehicle.setRemain(vehicle.getCapacity());
		List<VehicleApply> vehicleApplyList = vehicleApplyServiceImpl.findList("vehicleId", id);
		Iterator<VehicleApply> it = vehicleApplyList.iterator();
		while(it.hasNext()){
			VehicleApply vehicleApplyTemp = it.next();
			//vehicleApplyTemp.setVehicleId(0L);
			vehicleApplyTemp.setStatus(3);
			vehicleApplyServiceImpl.update(vehicleApplyTemp);
		}
		
		vehicleServiceImpl.update(vehicle);
		return "";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="del",method=RequestMethod.GET)
	public String delete(Vehicle vehicle){
		vehicleServiceImpl.deleteById(vehicle.getId());
		return "success/success";
	}
	

}
