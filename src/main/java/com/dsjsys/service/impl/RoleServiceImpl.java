package com.dsjsys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dsjsys.mapper.RoleMapper;
import com.dsjsys.pojo.Role;
import com.dsjsys.service.RoleService;
import com.dsjsys.tools.core.mapper.util.Pager;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapperImpl;

	@Override
	public Role findOne(String property, Object value) {
		return roleMapperImpl.findOne(property, value);
	}

	@Override
	public List<Role> findAll() {
		return roleMapperImpl.findAll();
	}

	@Override
	public List<Role> findList(String property, Object value) {
		return roleMapperImpl.findList(property, value);
	}
	
	 
	@Override
	public Pager<Role> queryPage(Map<String, Object> condition,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		if(condition==null)
		condition=new HashMap<String,Object>();
		condition.put("orderBy", orderBy);
		condition.put("sortBy", sortBy);
		Pager<Role> rolePager = roleMapperImpl.queryPage(condition, currentPage, pageSize);
		return rolePager;
	}

	@Override
	public Role save(Role role) {

		return roleMapperImpl.insert(role);
	}

	@Override
	public void deleteById(Long id) {
		roleMapperImpl.deleteById(id);
	}

	@Override
	public Role fetch(Long id) {
		return roleMapperImpl.fetch(id);
	}

	@Override
	public void update(Role role) {
		roleMapperImpl.update(role);
	}
}
