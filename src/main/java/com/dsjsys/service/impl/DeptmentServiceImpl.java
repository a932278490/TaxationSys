package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dsjsys.mapper.DeptmentMapper;
import com.dsjsys.pojo.Deptment;
import com.dsjsys.service.DeptmentService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
public class DeptmentServiceImpl implements DeptmentService {
	@Resource
	private DeptmentMapper deptmentMapperImpl;

	@Override
	public Deptment findOne(String property, Object value) {
		return deptmentMapperImpl.findOne(property, value);
	}

	@Override
	public List<Deptment> findAll() {
		return deptmentMapperImpl.findAll();
	}

	@Override
	public List<Deptment> findList(String property, Object value) {
		return deptmentMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<Deptment> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<Deptment> deptmentPager = deptmentMapperImpl.queryPage(condition, currentPage, pageSize);
		return deptmentPager;
	}

	@Override
	public Deptment save(Deptment deptment) {

		return deptmentMapperImpl.insert(deptment);
	}

	@Override
	public void deleteById(Long id) {
		deptmentMapperImpl.deleteById(id);
	}

	@Override
	public Deptment fetch(Long id) {
		return deptmentMapperImpl.fetch(id);
	}

	@Override
	public void update(Deptment deptment) {
		deptmentMapperImpl.update(deptment);
	}
}
