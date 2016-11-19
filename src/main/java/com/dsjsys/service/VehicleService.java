package com.dsjsys.service;

import java.util.List;
import java.util.Map;

import com.dsjsys.pojo.Vehicle;
import com.dsjsys.tools.core.mapper.util.Pager;

public interface VehicleService {
	/**
	 *修改单条数据
	 *@param project 当前实体（id不能为空）
	 */
	public void update(Vehicle vehicle);
	
	/**
	 *根据id查找单条数据
	 *@param id 查询id
	 *@return Project
	 */
	public Vehicle fetch(Long id);
	
	/**
	 *根据id删除单条数据
	 *@param id
	 */
	public void deleteById(Long id);
	
	/**
	 *保存单条数据
	 *@param project 当前实体
	 *@return Project
	 */
	public Vehicle save(Vehicle vehicle);
	
	/**
	 *根据单个条件查询单条数据
	 *@param property 字段名
	 *@param value 字段值
	 *@return Project
	 */
	public Vehicle findOne(String property,Object value);
	
	/**
	 *查询所有数据
	 *@return List
	 */
	public List<Vehicle> findAll();
	
	/**
	 *根据单个条件查询一条或多条数据
	 *@param property 字段名
	 *@param value 字段值
	 *@return List
	 */
	public List<Vehicle> findList(String property,Object value);

	/**
	 *根据条件查询一条或多条数据，带分页
	 *@param condition 查询条件
	 *@param currentPage 当前页数
	 *@param pageSize 每页条数
	 *@param orderBy 排序字段
	 *@param sortBy 排序方法（正序ASC,倒序DESC）
	 *@return Pager（getCurrentPage()当前页数，getDataList()数据List集合，getPageCount()总页数，getPageSize()每页条数，getTotalCount()总条数）
	 */
	public Pager<Vehicle> queryPage(Map<String,Object> condition,Integer currentPage,Integer pageSize,String orderBy,String sortBy);
}