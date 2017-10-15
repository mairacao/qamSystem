package com.xiaoyu.web.core;

import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
	
	void save(T model);// 持久化

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * 
	 * @param id
	 */
	void deleteByPrimaryKey(Long id);// 通过主鍵刪除

	/**
	 *  根据主键更新属性不为null的值
	 * @param model
	 */
	void updateByPrimaryKey(T model);// 更新

	T findById(Long id);// 通过ID查找

	T findBy(String fieldName, Object value) throws TooManyResultsException; // 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

	List<T> findAll();// 获取所有
}
