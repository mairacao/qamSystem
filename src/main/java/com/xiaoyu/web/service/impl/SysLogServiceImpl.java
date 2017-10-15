package com.xiaoyu.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysLogMapper;
import com.xiaoyu.web.model.SysLog;
import com.xiaoyu.web.service.SysLogService;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {
	
	@Resource
	private SysLogMapper sysLogMapper;


	@Override
	public PageInfo selectPage(Map params) {
		System.out.println(JSON.toJSONString(params));
		PageHelper.startPage(params);
		if(params.containsKey("sort")){
			PageHelper.orderBy(PMSUtils.isNull(params.get("sort")) + " " + PMSUtils.isNull(params.get("order")));
		}
		List list = sysLogMapper.selectAll();

		return new PageInfo(list);
	}

}
