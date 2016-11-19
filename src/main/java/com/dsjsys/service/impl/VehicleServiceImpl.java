package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsjsys.mapper.VehicleMapper;
import com.dsjsys.pojo.Vehicle;
import com.dsjsys.service.VehicleService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService{
	@Resource
	private VehicleMapper vehicleMapperImpl;

	@Override
	public Vehicle findOne(String property, Object value) {
		return vehicleMapperImpl.findOne(property, value);
	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleMapperImpl.findAll();
	}

	@Override
	public List<Vehicle> findList(String property, Object value) {
		return vehicleMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<Vehicle> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<Vehicle> vehiclePager = vehicleMapperImpl.queryPage(condition, currentPage, pageSize);
		return vehiclePager;
	}

	@Override
	public Vehicle save(Vehicle vehicle) {

		return vehicleMapperImpl.insert(vehicle);
	}

	@Override
	public void deleteById(Long id) {
		vehicleMapperImpl.deleteById(id);
	}

	@Override
	public Vehicle fetch(Long id) {
		return vehicleMapperImpl.fetch(id);
	}

	@Override
	public void update(Vehicle vehicle) {
		vehicleMapperImpl.update(vehicle);
	}
}
