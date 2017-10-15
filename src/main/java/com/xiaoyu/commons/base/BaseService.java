package com.xiaoyu.commons.base;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param record
	 * @return
	 */
	public List<T> select(T record);

	/**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 * 
	 * @param record
	 * @return
	 */
	public int selectCount(T record);

	/**
	 * 根据主键进行查询,必须保证结果唯一 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param key
	 * @return
	 */
	public T selectByPrimaryKey(Object key);

	/**
	 * 插入一条数据 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param record
	 * @return
	 */
	public int insert(T record);

	/**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param record
	 * @return
	 */
	public int insertSelective(T record);

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param key
	 * @return
	 */
	public int delete(T key);

	/**
	 * 通过主键进行删除,这里最多只会删除一条数据 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param key
	 * @return
	 */
	public int deleteByPrimaryKey(Object key);

	/**
	 * 根据主键进行更新,这里最多只会更新一条数据 参数为实体类
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(T record);

	/**
	 * 根据主键进行更新 只会更新不是null的数据
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 保存或者更新，根据传入id主键是不是null来确认
	 * 
	 * @param record
	 * @return 影响行数
	 */
//	public int save(T record);

}
