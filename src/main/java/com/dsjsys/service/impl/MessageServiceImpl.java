package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dsjsys.mapper.MessageMapper;
import com.dsjsys.pojo.Message;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.MessageService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
public class MessageServiceImpl implements MessageService {
	@Resource
	private MessageMapper messageMapperImpl;

	@Override
	public Message findOne(String property, Object value) {
		return messageMapperImpl.findOne(property, value);
	}

	@Override
	public List<Message> findAll() {
		return messageMapperImpl.findAll();
	}

	@Override
	public List<Message> findList(String property, Object value) {
		return messageMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<Message> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<Message> messagePager = messageMapperImpl.queryPage(condition, currentPage, pageSize);
		return messagePager;
	}

	@Override
	public Message save(Message message) {

		return messageMapperImpl.insert(message);
	}

	@Override
	public void deleteById(Long id) {
		messageMapperImpl.deleteById(id);
	}

	@Override
	public Message fetch(Long id) {
		return messageMapperImpl.fetch(id);
	}

	@Override
	public void update(Message message) {
		messageMapperImpl.update(message);
	}
	
	public void sendMessage(Stuff fromStuff, List<Stuff> toStuff,String content){
		for(int i =0;i<toStuff.size();i++){
			Message message = new Message();
			message.setStuffId(toStuff.get(i).getId());
			message.setContent(fromStuff.getName()+content);
			this.messageMapperImpl.insert(message);
		}
	}
}
