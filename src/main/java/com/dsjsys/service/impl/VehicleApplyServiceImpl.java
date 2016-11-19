package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsjsys.mapper.VehicleApplyMapper;
import com.dsjsys.pojo.VehicleApply;
import com.dsjsys.service.VehicleApplyService;
import com.dsjsys.tools.core.mapper.util.Pager;


@Service
@Transactional
public class VehicleApplyServiceImpl implements VehicleApplyService {
	
	@Resource
	private VehicleApplyMapper vehicleApplyMapperImpl;

	@Override
	public VehicleApply findOne(String property, Object value) {
		return vehicleApplyMapperImpl.findOne(property, value);
	}

	@Override
	public List<VehicleApply> findAll() {
		return vehicleApplyMapperImpl.findAll();
	}

	@Override
	public List<VehicleApply> findList(String property, Object value) {
		return vehicleApplyMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<VehicleApply> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<VehicleApply> vehicleApplyPager = vehicleApplyMapperImpl.queryPage(condition, currentPage, pageSize);
		return vehicleApplyPager;
	}

	@Override
	public VehicleApply save(VehicleApply vehicleApply) {

		return vehicleApplyMapperImpl.insert(vehicleApply);
	}

	@Override
	public void deleteById(Long id) {
		vehicleApplyMapperImpl.deleteById(id);
	}

	@Override
	public VehicleApply fetch(Long id) {
		return vehicleApplyMapperImpl.fetch(id);
	}

	@Override
	public void update(VehicleApply vehicleApply) {
		vehicleApplyMapperImpl.update(vehicleApply);
	}
}

