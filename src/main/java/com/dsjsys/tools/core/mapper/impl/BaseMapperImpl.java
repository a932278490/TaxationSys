package com.dsjsys.tools.core.mapper.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.dsjsys.tools.core.mapper.BaseMapper;
import com.dsjsys.tools.core.mapper.util.DetailsPager;
import com.dsjsys.tools.core.mapper.util.Pager;
import com.dsjsys.tools.core.mapper.util.uuid.IdUtil;
import com.dsjsys.tools.core.util.ReflectUtil;
import com.dsjsys.tools.core.util.StringUtil;


@Repository
public abstract class BaseMapperImpl<T, PK extends Serializable> extends
		SqlSessionDaoSupport implements BaseMapper<T, PK> {


	private Class<T> entityClass = null;

	/**
	 * 创建默认构造方法，以取得真正的泛型类型
	 * 
	 * @author vurtne
	 */
	@SuppressWarnings("unchecked")
	public BaseMapperImpl() {

		Class<?> c = getClass();

		Type type = c.getGenericSuperclass();

		if (type instanceof ParameterizedType) {

			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();

			entityClass = (Class<T>) parameterizedType[0];

		}

	}

	@Resource(name = "sqlSessionFactory")
	public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	// 保存实体对象
	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public T insert(T entity) {

		try {

			Field[] fileds = entity.getClass().getDeclaredFields();

			for (Field field : fileds) {

				if ((new StringUtil().lowerCaseFirstChar(entity.getClass().getSimpleName())+"Id").equals(field.getName())) {

					Method setIdMethod = entityClass.getDeclaredMethod(new ReflectUtil().setter(field.getName()),String.class);

					setIdMethod.invoke(entity, getPrimaryKeyValue());
					
					break;

				}

			}

		} catch (SecurityException e) {

			e.printStackTrace();

		} catch (NoSuchMethodException e) {

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		} catch (InvocationTargetException e) {

			e.printStackTrace();

		}

		getSqlSession().insert(entityClass.getName() + "Mapper.insert",entity);

		return entity;

	}

	// 更新
	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public void update(T entity) {

		getSqlSession().update(entityClass.getName() + "Mapper.update",entity);

	}

	// 根据id删除某个对象
	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public void deleteById(PK id) {

		getSqlSession().delete(entityClass.getName() + "Mapper.deleteById", id);

	}

	// 根据id加载某个对象
	@Override
	@Cacheable(value = "ehcache", key = "#id")
	public T fetch(PK id) {

		return getSqlSession().selectOne(entityClass.getName() + "Mapper.fetch", id);

	}

	// 查找所有的对象
	@Override
	public List<T> findAll() {

		return getSqlSession().selectList(entityClass.getName() + "Mapper.queryList", null);

	}

	// 根据查询参数，当前页数，每页显示的数目得到分页列表
	@Override
	@Cacheable(value = "ehcache", key = "'queryPage-'+#condition+'-'+#currentPage+'-'+#pageSize")
	public Pager<T> queryPage(Map<String, Object> condition,Integer currentPage, Integer pageSize) {

		return queryPage("queryList", "count", condition, currentPage, pageSize);

	}

	// 根据查询参数，当前页数，每页显示的数目得到分页列表
	@Override
	@Cacheable(value = "ehcache", key = "'queryPage-'+#condition+'-'+#currentPage+'-'+#pageSize")
	public Pager<T> queryPage(String dataSelectId, String countSelectId,Map<String, Object> condition, Integer currentPage, Integer pageSize) {

		Pager<T> pager = new Pager<T>(pageSize,count(countSelectId, condition), currentPage);

		try {

			if (condition == null) {

				condition = new HashMap<String, Object>();

			}

			condition.put("beginRow",(pager.getCurrentPage() - 1) * pager.getPageSize());

			condition.put("pageSize", pager.getPageSize());

			List<T> dataList = this.getSqlSession().selectList(entityClass.getName() + "Mapper." + dataSelectId,condition);

			pager.setDataList(dataList);

			return pager;

		} catch (RuntimeException re) {

			re.printStackTrace();

		}

		return null;

	}

	@Override
	public int count(String countSelectId, Map<String, Object> condition) {

		return getSqlSession().selectOne(entityClass.getName() + "Mapper." + countSelectId, condition);

	}

	@Override
	public int count(Map<String, Object> condition) {

		return getSqlSession().selectOne(entityClass.getName() + "Mapper.count", condition);

	}

	@Override
	@Cacheable(value = "ehcache", key = "'queryList-'+#condition+'-orderBy-'+#orderBy+'-sortBy-'+#sortBy")
	public List<T> queryList(Map<String, Object> condition, String orderBy,String sortBy) {

		if (condition == null) {

			condition = new HashMap<String, Object>();

		}

		condition.put("orderBy", orderBy);

		condition.put("sortBy", sortBy);

		return getSqlSession().selectList(entityClass.getName() + "Mapper.queryList", condition);

	}

	@Override
	@Cacheable(value = "ehcache", key = "'queryOne-'+#condition")
	public T queryOne(Map<String, Object> condition) {

		return getSqlSession().selectOne(entityClass.getName() + "Mapper.queryOne", condition);

	}

	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public T updateOrSave(T t, PK id) {

		if (null != fetch(id)) {

			update(t);

		} else {

			return insert(t);

		}

		return t;

	}

	@Override
	@Cacheable(value = "ehcache", key = "'findOne-'+#property+'-'+#value")
	public T findOne(String property, Object value) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(property, value);
		return getSqlSession().selectOne(entityClass.getName() + "Mapper.findOne", condition);

	}

	@Override
	@Cacheable(value = "ehcache", key = "'findList-'+#property+'-'+#value")
	public List<T> findList(String property, Object value) {

		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(property, value);

		return getSqlSession().selectList(entityClass.getName() + "Mapper.findList", condition);

	}

	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public void deleteByCondition(Map<String, Object> condition) {

		getSqlSession().delete(entityClass.getName() + "Mapper.deleteByCondition", condition);

	}

	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public void deleteByProperty(String property, Object value) {

		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(property, value);

		deleteByCondition(condition);

	}

	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public void updateNull(T entity) {

		getSqlSession().update(entityClass.getName() + "Mapper.updateNull",entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "ehcache", key = "'selectOne-'+#mapperId+'-'+#obj")
	public <O> O selectOne(String mapperId, Object obj) {

		Object returnObj = getSqlSession().selectOne(entityClass.getName() + "Mapper." + mapperId, obj);

		if (returnObj != null) {

			return (O) returnObj;

		}
		return null;

	}

	@Override
	@Cacheable(value = "ehcache", key = "'selectList-'+#mapperId+'-'+#obj")
	public <O> List<O> selectList(String mapperId, Object obj) {

		return getSqlSession().selectList(entityClass.getName() + "Mapper." + mapperId, obj);

	}

	@Override
	@CacheEvict(value = "ehcache", allEntries = true)
	public List<T> insertList(List<T> entities) {
		
		List<T> list = new ArrayList<T>();
		
		for (T t : entities) {
			
			list.add(insert(t));
			
		}
		
		return list;

	}

	@Override
	public DetailsPager<T> queryDetailsPage(Map<String, Object> condition, PK id) {

		T currentObj = fetch(id);

		if (currentObj != null) {

			List<PK> ids = getSqlSession().selectList(entityClass.getName() + "Mapper.findIds", condition);

			int currentObjIndex = ids.indexOf(id);

			DetailsPager<T> page = new DetailsPager<T>(currentObj);

			if (currentObjIndex > 0)

				page.setPreObj(fetch(ids.get(currentObjIndex - 1)));

			if (currentObjIndex < ids.size() - 1)

				page.setNextObj(fetch(ids.get(currentObjIndex + 1)));

			return page;

		}

		return null;

	}

	@Override
	public List<T> like(String property, Object value) {

		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put(property, value);

		return getSqlSession().selectList(entityClass.getName() + "Mapper.like", condition);

	}

	@Override
	public List<T> multiLike(Object value, String... properties) {

		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put("value", value);

		for (String string : properties) {

			condition.put(string, string);

		}

		return getSqlSession().selectList(entityClass.getName() + "Mapper.multiLike", condition);

	}
	/**
	 * 生成32位uuid
	 * @return
	 */
	protected String getPrimaryKeyValue() {

		return IdUtil.getId();

	}

}