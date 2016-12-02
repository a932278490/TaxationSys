package com.dsjsys.controller.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsjsys.annotation.AuthLevel;
import com.dsjsys.annotation.SystemControllerLog;
import com.dsjsys.config.AuthConfig;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.pojo.Message;
import com.dsjsys.pojo.Role;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.pojo.Vehicle;
import com.dsjsys.pojo.VehicleApply;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.service.MessageService;
import com.dsjsys.service.RoleService;
import com.dsjsys.service.StuffService;
import com.dsjsys.service.VehicleApplyService;
import com.dsjsys.service.VehicleService;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.mapper.util.uuid.IdUtil;
import com.dsjsys.tools.core.util.DateUtil;
import com.dsjsys.tools.core.util.JsonMessage;
import com.dsjsys.tools.core.util.Status;
import com.dsjsys.tools.core.util.WordUtil;

@Controller
@Transactional
@RequestMapping("/admin/vehicle/apply")
public class ApplyController {
	
	@Resource
	private StuffService stuffServiceImpl;
	
	@Resource
	private DeptmentService deptmentServiceImpl;
	
	@Resource
	private VehicleApplyService vehicleApplyServiceImpl;
	
	@Resource
	private VehicleService vehicleServiceImpl;
	
	@Resource
	private MessageService messageServiceImpl;
	
	@Resource
	private RoleService roleServiceImpl;
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="查看出车申请列表")
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest req,Integer currentPage,Integer pageSize){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<VehicleApply> vehicleApplyPager = vehicleApplyServiceImpl.queryPage(condition, currentPage, pageSize,"order_date","DESC");
		for(int i=0;i<vehicleApplyPager.getDataList().size();i++){
			Deptment deptment = deptmentServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getDeptId());
			Vehicle vehicle = vehicleServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getVehicleId());
			vehicleApplyPager.getDataList().get(i).setDeptment(deptment);
			vehicleApplyPager.getDataList().get(i).setVehicle(vehicle);
		}
		req.setAttribute("vehicleApplyPager", vehicleApplyPager);
		return "admin/vehicle/apply/apply_list";
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="查看出车申请列表1")
	@RequestMapping(value="list1",method=RequestMethod.GET)
	public String list1(HttpServletRequest req,Integer currentPage,Integer pageSize){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<VehicleApply> vehicleApplyPager = vehicleApplyServiceImpl.queryPage(condition, currentPage, pageSize,"order_date","DESC");
		for(int i=0;i<vehicleApplyPager.getDataList().size();i++){
			Deptment deptment = deptmentServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getDeptId());
			Vehicle vehicle = vehicleServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getVehicleId());
			vehicleApplyPager.getDataList().get(i).setDeptment(deptment);
			vehicleApplyPager.getDataList().get(i).setVehicle(vehicle);
		}
		req.setAttribute("vehicleApplyPager", vehicleApplyPager);
		return "admin/vehicle/apply/apply_list1";
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="出车申请详情")
	@RequestMapping(value="list2",method=RequestMethod.GET)
	public String list2(HttpServletRequest req,Integer currentPage,Integer pageSize){
		HttpSession session = req.getSession();
		Stuff loginStuff = (Stuff)session.getAttribute("loginStuff");
		List<Message> messageList = messageServiceImpl.findList("stuffId", loginStuff.getId());
		Iterator<Message> it = messageList.iterator();
		List<VehicleApply> vehicleApplyList = new ArrayList<VehicleApply>();
		while(it.hasNext()){
			Message message = (Message)it.next();
			int len = message.getContent().split(":").length;
			if(len<2){
				messageServiceImpl.deleteById(message.getId());
				continue;
			}
			Long applyId= Long.parseLong(message.getContent().split(":")[1]);
			VehicleApply vehicleApply = vehicleApplyServiceImpl.fetch(applyId);
			vehicleApply.setDeptment(deptmentServiceImpl.fetch(vehicleApply.getDeptId()));
			vehicleApply.setStuff(stuffServiceImpl.fetch(vehicleApply.getStuffId()));
			vehicleApply.setVehicle(vehicleServiceImpl.fetch(vehicleApply.getVehicleId()));
			vehicleApplyList.add(vehicleApply);
			messageServiceImpl.deleteById(message.getId());
		}
		if(loginStuff.getDeptment().getName().equals("机关车队")){
			return "redirect:/admin/vehicle/list";
		}
		req.setAttribute("vehicleApplyList", vehicleApplyList);
		
		return "admin/vehicle/apply/apply_list2";
	}
	@AuthLevel(level=AuthConfig.level1)
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest req){
		List<Deptment> deptmentList = deptmentServiceImpl.findAll();
		req.setAttribute("deptmentList", deptmentList);
		return "admin/vehicle/apply/apply_add";
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="新增一条申请")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(HttpServletRequest req,VehicleApply vehicleApply,String beginDate ,String endDate){
		vehicleApply.setId(IdUtil.getDatetimeId());
		vehicleApply.setBeginDate(DateUtil.parse(beginDate));
		vehicleApply.setEndDate(DateUtil.parse(endDate));
		vehicleApply.setOrderDate(new Date());
		vehicleApply.setStatus(0);
		VehicleApply vehicleApplyNew = vehicleApplyServiceImpl.save(vehicleApply);
		/***************************消息发送处理，可以放在切面类中**************************************************************/
		Stuff loginStuff = (Stuff)req.getSession().getAttribute("loginStuff");
		Deptment deptment = this.deptmentServiceImpl.fetch(loginStuff.getDeptId());
		List<Stuff> stuffList = new ArrayList<Stuff>();
		Stuff stuffChange = stuffServiceImpl.findOne("name", deptment.getCharge());
		stuffList.add(stuffChange);
		this.messageServiceImpl.sendMessage(loginStuff, stuffList, "新增一条申请！申请编号为:"+vehicleApplyNew.getId());
		/*********************************************************************************/
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@RequestMapping(value="stop",method=RequestMethod.GET)
	public String stop(Long id){
		VehicleApply vehicleApply = new VehicleApply();
		vehicleApply.setId(id);
		vehicleApply.setStatus(0);
		vehicleApplyServiceImpl.update(vehicleApply);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="审批通过一条申请")
	@RequestMapping(value="start",method=RequestMethod.POST)
	public String start(HttpServletRequest req,Long stuffId,Long applyId){
		HttpSession session = req.getSession();
		Stuff loginStuff = (Stuff)session.getAttribute("loginStuff");
		if(loginStuff.getRole().getLevel()==2){
			VehicleApply vehicleApply = new VehicleApply();
			vehicleApply.setId(applyId);
			vehicleApply.setStatus(1);
			vehicleApplyServiceImpl.update(vehicleApply);
			Stuff stuff = this.stuffServiceImpl.fetch(stuffId);
			List<Stuff> stuffList = new ArrayList<Stuff>();
			stuffList.add(stuff);
			this.messageServiceImpl.sendMessage(loginStuff, stuffList, loginStuff.getName()+"部门负责人审批通过！审批编号:"+applyId);
		}else if(loginStuff.getRole().getLevel()==3){
			VehicleApply vehicleApply = new VehicleApply();
			vehicleApply.setId(applyId);
			vehicleApply.setStatus(2);
			vehicleApplyServiceImpl.update(vehicleApply);
			Stuff stuff1 = this.stuffServiceImpl.findOne("name", "王志谦");
			Stuff stuff2 = this.stuffServiceImpl.findOne("name", "马新军");
			List<Stuff> stuffList = new ArrayList<Stuff>();
			stuffList.add(stuff1);
			stuffList.add(stuff2);
			this.messageServiceImpl.sendMessage(loginStuff, stuffList, loginStuff.getName()+",局领导审批通过！审批编号:"+applyId);
		}else{
			VehicleApply vehicleApply = new VehicleApply();
			vehicleApply.setId(applyId);
			vehicleApply.setStatus(0);
			Stuff stuff = this.stuffServiceImpl.findOne("name", "王志谦");
			List<Stuff> stuffList = new ArrayList<Stuff>();
			stuffList.add(stuff);
			this.messageServiceImpl.sendMessage(loginStuff, stuffList, ",未知人员审批已阻止！");
		}
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="回绝申请")
	@RequestMapping(value="refuse",method=RequestMethod.GET)
	public String refuse(HttpServletRequest req,Long id){
		VehicleApply vehicleApply = vehicleApplyServiceImpl.fetch(id);
		vehicleApply.setStatus(4);
		vehicleApplyServiceImpl.update(vehicleApply);
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="通知领导")
	@RequestMapping(value="adviceManger",method=RequestMethod.GET)
	public String adviceManager(HttpServletRequest req,Long id){
		List<Stuff> stuffList = this.stuffServiceImpl.findList("deptId", 3);
		req.setAttribute("stuffList", stuffList);
		req.setAttribute("id", id);
		return "admin/vehicle/apply/adviceManger";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="删除一条申请")
	@RequestMapping(value="del",method=RequestMethod.POST)
	public String delete(VehicleApply vehicleApply){
		vehicleApplyServiceImpl.deleteById(vehicleApply.getId());
		return "success/success";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="打印一条申请证明")
	@RequestMapping(value="word",method=RequestMethod.GET)
	public ModelAndView printWord(HttpServletRequest req,VehicleApply vehicleApply){
		vehicleApply = this.vehicleApplyServiceImpl.fetch(vehicleApply.getId());
		vehicleApply.setDeptment(deptmentServiceImpl.fetch(vehicleApply.getDeptId()));
		vehicleApply.setVehicle(this.vehicleServiceImpl.fetch(vehicleApply.getVehicleId()));
		vehicleApply.setStuff(this.stuffServiceImpl.fetch(vehicleApply.getStuffId()));
		WordUtil wu = new WordUtil();
		Map<String,Object> dataMap= new HashMap<String, Object>();
		dataMap.put("deptmentName", vehicleApply.getDeptment().getName());
		dataMap.put("loginStuffName",vehicleApply.getStuff().getName());
		dataMap.put("vehicleReason", vehicleApply.getReason());
		dataMap.put("destination", vehicleApply.getDestination());
		dataMap.put("pcount", vehicleApply.getPcount());
		dataMap.put("beginDate",vehicleApply.getBeginDate());
		dataMap.put("endDate",vehicleApply.getEndDate());
		dataMap.put("chargeOpinion","审批通过");
		dataMap.put("officeOpinion","审批通过");
		dataMap.put("leaderOpinion","审批通过");
		dataMap.put("remarks","车牌号:"+vehicleApply.getVehicle().getLicence()+","+"司机:"+vehicleApply.getVehicle().getDriver());
		Calendar c = Calendar.getInstance();
		c.setTime(vehicleApply.getOrderDate());
		
		dataMap.put("year", c.get(Calendar.YEAR));
		dataMap.put("month",c.get(Calendar.MONTH)+1);
		dataMap.put("day", c.get(Calendar.DAY_OF_MONTH));
		return new ModelAndView("admin/word/dsj",dataMap);  
	}
	
/*	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="导出一条申请证明")
	@RequestMapping(value="print",method=RequestMethod.GET)
	public ResponseEntity<byte[]> print(HttpServletRequest req,HttpServletResponse response,VehicleApply vehicleApply) throws IOException{
		
		vehicleApply = this.vehicleApplyServiceImpl.fetch(vehicleApply.getId());
		vehicleApply.setDeptment(deptmentServiceImpl.fetch(vehicleApply.getDeptId()));
		vehicleApply.setStuff(this.stuffServiceImpl.fetch(vehicleApply.getStuffId()));
		Stuff loginStuff = (Stuff)req.getSession().getAttribute("loginStuff");
		WordUtil wu = new WordUtil();
		Map<String,Object> dataMap= new HashMap<String, Object>();
		dataMap.put("deptmentName", vehicleApply.getDeptment().getName());
		dataMap.put("loginStuffName",loginStuff.getName());
		dataMap.put("vehicleReason", vehicleApply.getReason());
		dataMap.put("destination", vehicleApply.getDestination());
		dataMap.put("pcount", vehicleApply.getPcount());
		dataMap.put("beginDate",vehicleApply.getBeginDate());
		dataMap.put("endDate",vehicleApply.getEndDate());
		dataMap.put("chargeOpinion","审批通过");
		dataMap.put("officeOpinion","审批通过");
		dataMap.put("leaderOpinion","审批通过");
		dataMap.put("remarks","无");
		Calendar c = Calendar.getInstance();
		c.setTime(vehicleApply.getOrderDate());
		
		dataMap.put("year", c.get(Calendar.YEAR));
		dataMap.put("month",c.get(Calendar.MONTH)+1);
		dataMap.put("day", c.get(Calendar.DAY_OF_MONTH));
		
		String fileTemp =  vehicleApply.getId()+"号审批单";
		wu.createWord(dataMap,fileTemp);
        String path="D:\\"+fileTemp+".doc";  
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String((fileTemp+".doc").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);   
	}*/
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="导出一条申请证明")
	@RequestMapping(value="export",method=RequestMethod.GET)
	public String export(HttpServletRequest req,HttpServletResponse response,VehicleApply vehicleApply) throws IOException{
		
		vehicleApply = this.vehicleApplyServiceImpl.fetch(vehicleApply.getId());
		vehicleApply.setDeptment(deptmentServiceImpl.fetch(vehicleApply.getDeptId()));
		vehicleApply.setStuff(this.stuffServiceImpl.fetch(vehicleApply.getStuffId()));
		WordUtil wu = new WordUtil();
		Map<String,Object> dataMap= new HashMap<String, Object>();
		dataMap.put("deptmentName", vehicleApply.getDeptment().getName());
		dataMap.put("loginStuffName",vehicleApply.getStuff().getName());
		dataMap.put("vehicleReason", vehicleApply.getReason());
		dataMap.put("destination", vehicleApply.getDestination());
		dataMap.put("pcount", vehicleApply.getPcount());
		dataMap.put("beginDate",vehicleApply.getBeginDate());
		dataMap.put("endDate",vehicleApply.getEndDate());
		dataMap.put("chargeOpinion","审批通过");
		dataMap.put("officeOpinion","审批通过");
		dataMap.put("leaderOpinion","审批通过");
		dataMap.put("remarks","无");
		Calendar c = Calendar.getInstance();
		c.setTime(vehicleApply.getOrderDate());
		
		dataMap.put("year", c.get(Calendar.YEAR));
		dataMap.put("month",c.get(Calendar.MONTH)+1);
		dataMap.put("day", c.get(Calendar.DAY_OF_MONTH));
		
		String fileTemp =  vehicleApply.getId()+"号审批单";
		wu.createWord(dataMap,fileTemp);
        String path="D:\\"+fileTemp+".doc";  
        File file=new File(path);  
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileTemp+".doc");
		try {
			InputStream inputStream = new FileInputStream(file);

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			 // 这里主要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response
		return null;
    }
	

	@AuthLevel(level=AuthConfig.level3)
	@RequestMapping(value="distributePage",method=RequestMethod.GET)
	public String distribute(HttpServletRequest req,Integer currentPage,Integer pageSize,Long vehicleId){
		Map<String,Object> condition=new HashMap<String,Object>();
		Pager<VehicleApply> vehicleApplyPager = vehicleApplyServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		for(int i=0;i<vehicleApplyPager.getDataList().size();i++){
			Deptment deptment = deptmentServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getDeptId());
			Vehicle vehicle = vehicleServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getVehicleId());
			vehicleApplyPager.getDataList().get(i).setDeptment(deptment);
			vehicleApplyPager.getDataList().get(i).setVehicle(vehicle);
		}
		Iterator<VehicleApply> it = vehicleApplyPager.getDataList().iterator();
		while(it.hasNext()){
			VehicleApply vehicleApplyTemp = it.next();
			if(vehicleApplyTemp.getStatus()==0 || vehicleApplyTemp.getStatus()==1 || vehicleApplyTemp.getVehicleId()!=0){
				it.remove();
				vehicleApplyPager.setTotalCount(vehicleApplyPager.getTotalCount()-1);
			}
		}
		req.setAttribute("vehicleApplyPager", vehicleApplyPager);
		req.setAttribute("vehicleId", vehicleId);
		return "admin/vehicle/distribute_page";
	}
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="分配车辆")
	@ResponseBody
	@RequestMapping(value="dodistribute",method=RequestMethod.POST)
	public JsonMessage doDistribute(HttpServletRequest req,VehicleApply vehicleApply){
		VehicleApply vehicleApplyTemp = vehicleApplyServiceImpl.fetch(vehicleApply.getId());
		Vehicle vehicleTemp = vehicleServiceImpl.fetch(vehicleApply.getVehicleId());
		int result = vehicleTemp.getRemain()-vehicleApplyTemp.getPcount();
		if(result<0){
			return new JsonMessage("","","",Status.ERROR);
		}
		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleTemp.getId());
		vehicle.setRemain(result);
		vehicleServiceImpl.update(vehicle);
		vehicleApplyServiceImpl.update(vehicleApply);
		return new JsonMessage("","","",Status.SUCCESS);
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@SystemControllerLog(description="查看乘车信息")
	@RequestMapping(value="distributeInfo",method=RequestMethod.GET)
	public String distributeInfo(HttpServletRequest req,Integer currentPage,Integer pageSize,Long vehicleId){
		Map<String,Object> condition=new HashMap<String,Object>();
		condition.put("vehicleId", vehicleId);
		Pager<VehicleApply> vehicleApplyPager = vehicleApplyServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		Iterator<VehicleApply> it = vehicleApplyPager.getDataList().iterator();
		while(it.hasNext()){
			VehicleApply vehicleTemp = it.next();
			if(vehicleTemp.getStatus()==3){
				it.remove();
			}
		}
		for(int i=0;i<vehicleApplyPager.getDataList().size();i++){
			Deptment deptment = deptmentServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getDeptId());
			Vehicle vehicle = vehicleServiceImpl.fetch(vehicleApplyPager.getDataList().get(i).getVehicleId());
			vehicleApplyPager.getDataList().get(i).setDeptment(deptment);
			vehicleApplyPager.getDataList().get(i).setVehicle(vehicle);
		}
		req.setAttribute("vehicleApplyPager", vehicleApplyPager);
		req.setAttribute("vehicleId", vehicleId);
		return "admin/vehicle/distribute_info";
	}
	
	
	@AuthLevel(level=AuthConfig.level3)
	@SystemControllerLog(description="取消分配车辆")
	@ResponseBody
	@RequestMapping(value="cancelDistribute",method=RequestMethod.POST)
	public JsonMessage cancelDistribute(HttpServletRequest req,VehicleApply vehicleApply){
		VehicleApply vehicleApplyTemp = vehicleApplyServiceImpl.fetch(vehicleApply.getId());
		Vehicle vehicleTemp = vehicleServiceImpl.fetch(vehicleApply.getVehicleId());
		int result = vehicleTemp.getRemain()+vehicleApplyTemp.getPcount();
		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleTemp.getId());
		vehicle.setRemain(result);
		vehicleApply.setVehicleId(0L);
		vehicleServiceImpl.update(vehicle);
		vehicleApplyServiceImpl.update(vehicleApply);
		return new JsonMessage("","","",Status.SUCCESS);
	}
}
