package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsjsys.mapper.StuffMapper;
import com.dsjsys.pojo.Stuff;
import com.dsjsys.service.StuffService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
@Transactional
public class StuffServiceImpl implements StuffService{

	@Resource
	private StuffMapper stuffMapperImpl;

	@Override
	public Stuff findOne(String property, Object value) {
		return stuffMapperImpl.findOne(property, value);
	}

	@Override
	public List<Stuff> findAll() {
		return stuffMapperImpl.findAll();
	}

	@Override
	public List<Stuff> findList(String property, Object value) {
		return stuffMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<Stuff> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<Stuff> stuffPager = stuffMapperImpl.queryPage(condition, currentPage, pageSize);
		return stuffPager;
	}

	@Override
	public Stuff save(Stuff stuff) {

		return stuffMapperImpl.insert(stuff);
	}

	@Override
	public void deleteById(Long id) {
		stuffMapperImpl.deleteById(id);
	}

	@Override
	public Stuff fetch(Long id) {
		return stuffMapperImpl.fetch(id);
	}

	@Override
	public void update(Stuff stuff) {
		stuffMapperImpl.update(stuff);
	}
}
