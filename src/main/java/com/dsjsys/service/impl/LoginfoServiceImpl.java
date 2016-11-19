package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsjsys.annotation.SystemServiceLog;
import com.dsjsys.mapper.LoginfoMapper;
import com.dsjsys.pojo.Loginfo;
import com.dsjsys.service.LoginfoService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
@Transactional
public class LoginfoServiceImpl implements LoginfoService {

	@Resource
	private LoginfoMapper loginfoMapperImpl;
	
	
	@Override
	public void update(Loginfo loginfo) {
		loginfoMapperImpl.update(loginfo);
	}

	@Override
	public Loginfo fetch(Long id) {
		return loginfoMapperImpl.fetch(id);
	}

	@Override
	public void deleteById(Long id) {
		loginfoMapperImpl.deleteById(id);
	}

	@Override
	public Loginfo save(Loginfo loginfo) {
		return loginfoMapperImpl.insert(loginfo);
	}

	@Override
	public Loginfo findOne(String property, Object value) {
		return loginfoMapperImpl.findOne(property, value);
	}

	@Override
	public List<Loginfo> findAll() {
		return loginfoMapperImpl.findAll();
	}

	@Override
	public List<Loginfo> findList(String property, Object value) {
		return loginfoMapperImpl.findList(property, value);
	}

	@Override

	public Pager<Loginfo> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
			condition=new HashMap<String,Object>();
			condition.put("orderBy", orderBy);
			condition.put("sortBy", sortBy);
		return loginfoMapperImpl.queryPage(condition, currentPage, pageSize);
	}

}
