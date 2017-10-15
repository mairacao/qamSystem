package com.xiaoyu.web.core;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.PMSUtils;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 基于mybatis实现继承service
 * @author ql
 *
 * @param <T>
 */
public abstract class ServiceMybatis<T> implements BaseService<T> {

	@Autowired
	protected Mapper<T> mapper;

	@Resource
	protected BaseMapper baseMapper;

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param <T
	 *            extend T>
	 */
	public List<T> select(T record) {
		return mapper.select(record);
	}

	public List<T> select(T record, String orderSqlStr) {
		Example example = new Example(record.getClass(), false);
		Criteria criteria = example.createCriteria();
		
		Map<String, Object> map = beanToMap(record);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (PMSUtils.isEmpty(entry.getValue()))
				continue;
			criteria.andEqualTo(entry.getKey(), entry.getValue());
		}
		example.setOrderByClause(orderSqlStr);
		return mapper.selectByExample(example);
	}

	/**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}

	/**
	 * 根据主键进行查询,必须保证结果唯一 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param <T
	 *            extend T>
	 */
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 插入一条数据 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int insert(T record) {
		return mapper.insert(record);
	}

	/**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int insertSelective(T record) {
		return mapper.insertSelective(record);
	}

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int delete(T key) {
		return mapper.delete(key);
	}

	/**
	 * 通过主键进行删除,这里最多只会删除一条数据 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 根据主键进行更新,这里最多只会更新一条数据 参数为实体类
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int updateByPrimaryKey(T record) {
		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据主键进行更新 只会更新不是null的数据
	 * 
	 * @param <T
	 *            extend T>
	 */
	public int updateByPrimaryKeySelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}


//	/**
//	 * 保存或者更新，根据传入id主键是不是null来确认
//	 * 
//	 * @param record
//	 * @return 影响行数
//	 */
//	public int save(T record) {
//		int count = 0;
//		return count;
//	}

	/**
	 * 单表分页
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            条数
	 * @param record
	 *            条件实体
	 * @return
	 */
	public PageInfo<T> selectPage(int pageNum, int pageSize, T record) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<T>(mapper.select(record));
	}

	/**
	 * @Description:(单表分页可排序)
	 * @param:@param pageNum
	 * @param:@param pageSize
	 * @param:@param record
	 * @param:@param orderSqlStr
	 *                   (如:id desc)
	 * @return:PageInfo<T>
	 */
	public PageInfo<T> selectPage(int pageNum, int pageSize, T record, String orderSqlStr) {
		Example example = new Example(record.getClass(), false);
		Criteria criteria = example.createCriteria();
		
		Map<String, Object> map = beanToMap(record);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (PMSUtils.isEmpty(entry.getValue()))
				continue;
			criteria.andEqualTo(entry.getKey(), entry.getValue());
		}
		example.setOrderByClause(orderSqlStr);
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = mapper.selectByExample(example);
		return new PageInfo<T>(list);
	}

	/**
	 * 将javabean实体类转为map类型，然后返回一个map类型的值
	 * @param obj
	 * @return
	 */
	private Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
}
