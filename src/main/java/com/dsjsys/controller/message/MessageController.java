package com.dsjsys.controller.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsjsys.annotation.AuthLevel;
import com.dsjsys.annotation.SystemControllerLog;
import com.dsjsys.config.AuthConfig;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.pojo.Message;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.MessageService;
import com.dsjsys.service.VehicleApplyService;
import com.dsjsys.service.impl.StuffServiceImpl;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.util.JsonMessage;

@Controller
@RequestMapping("/admin/message")
public class MessageController {
	
	@Resource
	private MessageService messageServiceImpl;
	
	@Resource
	private StuffServiceImpl stuffServiceImpl;
	
	@Resource
	private VehicleApplyService vehicleApplyServiceImpl;

	@ResponseBody
	@RequestMapping(value="messageInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonMessage messageInfo(Long stuffId){
		List<Message> messageList = messageServiceImpl.findList("stuffId", stuffId);
		return new JsonMessage(messageList.size(),"message","",1);
	}
	
	@AuthLevel(level=AuthConfig.level1)
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest req,Integer currentPage,Integer pageSize){
		/*Map<String,Object> condition=new HashMap<String,Object>();
		Pager<Message> messagePager = messageServiceImpl.queryPage(condition, currentPage, pageSize,null,null);
		res.setAttribute("messagePager", messagePager);*/
		Stuff LoginStuff=  (Stuff)req.getSession().getAttribute("loginStuff");
		
		List<Message> messageList = messageServiceImpl.findList("stuffId", LoginStuff.getId());
		req.setAttribute("messageList", messageList);
		return "admin/message/messageinfo_list";
	}
	
	@AuthLevel(level=AuthConfig.level2)
	@SystemControllerLog(description="删除消息信息")
	@RequestMapping(value="del",method=RequestMethod.POST)
	public String delete(Message message){
		messageServiceImpl.deleteById(message.getId());
		return "success/success";
	}
	
}
