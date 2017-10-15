package com.xiaoyu.web.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

	@Autowired
	protected Mapper<T> mapper;

	private Class<T> modelClass; // 当前泛型真实类型的Class

	public AbstractService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		modelClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void save(T model) {
		mapper.insertSelective(model);
	}

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 */
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据主键更新属性不为null的值
	 */
	public void updateByPrimaryKey(T model) {
		mapper.updateByPrimaryKeySelective(model);
	}

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 */
	public T findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public T findBy(String fieldName, Object value) throws TooManyResultsException {
		try {
			T model = modelClass.newInstance();
			Field field = modelClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(model, value);
			return mapper.selectOne(model);
		} catch (ReflectiveOperationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<T> findAll() {
		return mapper.selectAll();
	}
}
