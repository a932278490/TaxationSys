package com.dsjsys.tools.core.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.dsjsys.tools.core.mapper.util.DetailsPager;
import com.dsjsys.tools.core.mapper.util.Pager;

/**
 * 底层基本的dao的接口
 * 
 * @author lvzheng
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseMapper<T, PK> {

	/**
	 * 保存单一对象，如果要保存多个对象集合，请参看{insertList(List)}
	 * 
	 * @param entity
	 */
	public T insert(T entity);
	
	/**
	 * 保存指定类型的对象集合,如果要保存单一对象，请参看{insert(Object)}
	 * @param entities 要保存的数据集合
	 * @return
	 */
	public List<T> insertList(List<T> entities);

	/**
	 * 更新对象,如果属性为空，则不会进行对应的属性值更新,如果有属性要更新为null，请参看{updateNull(Object)}
	 * 
	 * @param entity
	 *            要更新的实体对象
	 */
	public void update(T entity);

	/**
	 * 更新对象,如果属性为空，会进行对应的属性值更新,如果有属性不想更新为null，请参看{update(Object)}
	 * 
	 * @param entity
	 */
	public void updateNull(T entity);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public void deleteById(PK id);

	/**
	 * 根据条件集合删除对象
	 * 
	 * @param id
	 */
	public void deleteByCondition(Map<String, Object> condition);

	/**
	 * 根据属性和属性值删除对象
	 * 
	 * @param id
	 */
	public void deleteByProperty(String property, Object value);

	/**
	 * 根据id进行对象查询
	 * 
	 * @param id
	 * @return
	 */
	public T fetch(PK id);

	/**
	 * 根据任意属性和属性值进行对象查询，如果返回多个对象，将抛出异常
	 * 
	 * @param property
	 *            进行对象匹配的属性
	 * @param value
	 *            进行对象匹配的属性值
	 * @return 返回泛型参数类型对象，如何取到泛型类型参数，请参看{getEntityClass()}
	 */
	public T findOne(String property, Object value);

	/**
	 * 根据任意（单一）属性和属性值进行集合查询
	 * 
	 * @param property
	 *            进行对象匹配的属性
	 * @param value
	 *            进行对象匹配的属性值
	 * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{getEntityClass()}
	 */
	public List<T> findList(String property, Object value);

	/**
	 * 根据传入的泛型参数类型查询该类型对应表中的所有数据，返回一个集合对象
	 * 
	 * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{getEntityClass()}
	 */
	public List<T> findAll();

	/**
	 * 根据条件集合进行分页查询
	 * 
	 * @param condition
	 *            查询条件
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            页面大小
	 * @return 返回Pager对象
	 */
	public Pager<T> queryPage(Map<String, Object> condition, Integer currentPage,Integer pageSize);	

	/**
	 * 根据条件集合进行分页查询
	 * 
	 * @param condition
	 *            查询条件
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            页面大小
	 * @return 返回Pager对象
	 */
	public Pager<T> queryPage(String dataSelectId,String countSelectId,Map<String, Object> condition, Integer currentPage,Integer pageSize);	

	/**
	 * 根据当前id进行详情分页
	 * @param condition 分页查询的条件
	 * @param id 当前id
	 * @return 返回当前对象，上一个对象，下一对象
	 */
	public DetailsPager<T> queryDetailsPage(Map<String, Object> condition, PK id);
	
	/**
	 * 根据任意属性和属性值进行对象模糊查询
	 * @param property
	 *            进行对象匹配的属性
	 * @param value
	 *            进行对象匹配的模糊属性值
	 * @return
	 */
	public List<T> like(String property, Object value);
	
	/**
	 * 根据属性列表和值进行模糊查询
	 * @param value 进行模糊匹配的值 
	 * @param properties进行模糊匹配的属性
	 * @return 返回集合对象列表
	 */
	public List<T> multiLike(Object value,String ...properties);

	/**
	 * 根据条件集合进行指定类型对象集合查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{getEntityClass()}
	 */
	public List<T> queryList(Map<String, Object> condition,String orderBy,String sortBy);
	
	/**
	 * 根据条件集合进行指定类型单一对象查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象，如何取到泛型类型参数，请参看{getEntityClass()}，
	 */
	public T queryOne(Map<String, Object> condition);

	/**
	 * 根据条件进行数量的查询
	 * 
	 * @param condition
	 * @return 返回符合条件的泛型参数对应表中的数量
	 */
	public int count(Map<String, Object> condition);

	/**
	 * 根据条件与指定sql进行数量的查询
	 * 
	 * @param condition
	 * @return 返回符合条件的泛型参数对应表中的数量
	 */
	int count(String countSelectId, Map<String, Object> condition);


	/**
	 * 更新或保存，涉及到Mabatis使用的bean只是一个简单的值对象，不能进行id的注解，不知道哪个是主键，所以，必须同时指定t的主键值
	 * 
	 * @param t
	 *            要更新或保存的对象
	 * @param id
	 *            要更新或保存的对象的id
	 * @return 返回更新或保存的对象。
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public T updateOrSave(T t, PK id);

	/**
	 * 根据泛型类型，执行最原始的sql
	 * 
	 * @param mapperId
	 * @param Object
	 * @return 返回泛型类型对象，如果返回多个结果集会抛出异常，如果要返回多个结果集，请参看
	 *         {selectList(String, String)}
	 */
	public <O> O selectOne(String mapperId, Object obj);

	/**
	 * 根据泛型类型，执行最原始的sql
	 * 
	 * @param mapperId
	 * @param Object
	 * @return 返回泛型类型对象集合，如果要返回单个结果对象，请参看{selectOne(String, String)}
	 */
	public <O> List<O> selectList(String mapperId, Object obj);


}
