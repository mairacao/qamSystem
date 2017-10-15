package com.xiaoyu.commons.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EasyuiUtils {

	/**
	 * easyui 分页参数转换
	 * @param params
	 */
	public static void getParams(Map params){
		
		params.put("pageNum", params.get("page"));
		params.put("pageSize", params.get("rows"));
		params.remove("page");
		params.remove("rows");
	}
	
	public static Map getResult(PageInfo page){
		List list = page.getList();
		long totals = page.getTotal();
		
		Map ret = new HashMap();
		ret.put("total", totals);
		ret.put("rows", list);
		return ret;
	}
	public static Map getResult(List pageList, int totalSize){
		
		Map ret = new HashMap();
		ret.put("total", totalSize);
		ret.put("rows", pageList);
		return ret;
	}
}
