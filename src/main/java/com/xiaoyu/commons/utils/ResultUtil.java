package com.xiaoyu.commons.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;

/**
 * 系统返回结果集封装
 * 
 * @author ql
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResultUtil {

	private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

	public static final String SUCCEED_CODE = "200";
	public static final String SUCCEED_MSG = "操作成功";
	public static final String ERROR_CODE = "500";
	public static final String ERROR_MSG = "操作失败";
	
	/** 错误代码 */
	protected static final String RESULT_CODE = "code";

	/** 错误描述 */
	protected static final String RESULT_DESC = "message";

	/**
	 * 请求成功返回结果封装
	 * 
	 * @return 返回【200 ：操作成功】消息提示信息
	 */
	public static Map getSuccessResult() {
		return getResult(SUCCEED_CODE, SUCCEED_MSG, null);
	}

	/**
	 * 请求成功返回结果封装
	 * 
	 * @param code_message
	 *            (可修改返回200成功的消息提示信息)
	 * @return
	 */
	public static Map getSuccessResult(String success_msg) {
		if (success_msg == null) {
			success_msg = SUCCEED_MSG;
		}
		return getResult(SUCCEED_CODE, success_msg, null);
	}

	/**
	 * 请求成功返回结果封装
	 * 
	 * @param objectData
	 *            返回附带的参数
	 * @return
	 */
	public static Map getSuccessResult(Object objectData) {
		return getResult(SUCCEED_CODE, SUCCEED_MSG, objectData);
	}

	/**
	 * 请求成功返回结果封装
	 * 
	 * @param code_message
	 *            (可修改返回200成功的消息提示信息)
	 * @param objectData
	 *            返回附带的参数
	 * @return
	 */
	public static Map getSuccessResult(String success_msg, Object objectData) {
		if (success_msg == null) {
			success_msg = SUCCEED_MSG;
		}
		return getResult(SUCCEED_CODE, success_msg, objectData);
	}

	/**
	 * 请求失败返回结果封装
	 * 
	 * @param error_msg
	 *            (返回500错误的消息提示信息)
	 * @return
	 */
	public static Map getErrorResult() {
		return getResult(ERROR_CODE, ERROR_MSG, null);
	}

	/**
	 * 请求失败返回结果封装
	 * 
	 * @param error_msg
	 *            (可修改返回500错误的消息提示信息)
	 * @return
	 */
	public static Map getErrorResult(String error_msg) {
		if (error_msg == null) {
			error_msg = ERROR_MSG;
		}
		return getResult(ERROR_CODE, error_msg, null);
	}

	/**
	 * 请求返回结果封装
	 * 
	 * @param code
	 *            返回代码（通过i18n配置文件中读取代码对应代码描述信息【未找到对应的信息 返回空字符串】）
	 * @param objectData
	 *            返回数据
	 * @return
	 */
//	public static Map getResult(String code, Object objectData) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		Map resultMap = new HashMap();
//		resultMap.put(RESULT_CODE, code);
//
//		try {
//			resultMap.put(RESULT_DESC, MessageUtils.getMessage(request, code));
//		} catch (Exception e) {
//			resultMap.put(RESULT_DESC, ""); // i18n文件没有对应的code信息返回空字符串
//		}
//
//		/*
//		 * 返回信息结果集合,如果是集合默认key值为'list' {'list' : list集合}
//		 */
//		getResultData(resultMap, objectData);
//
//		// logger.debug("返回结果json格式："+JSON.toJSONString(map,
//		// SerializerFeature.WriteMapNullValue));
//		logger.debug(JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue));
//		return resultMap;
//	}

	/**
	 * 请求返回结果封装
	 * 
	 * @param code
	 *            返回代码
	 * @param code_message
	 *            返回代码对应描述
	 * @param objectData
	 *            返回数据
	 * @return
	 */
	public static Map getResult(String code, String code_message, Object objectData) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		Map resultMap = new HashMap();
		resultMap.put(RESULT_CODE, code);
		resultMap.put(RESULT_DESC, code_message);
		resultMap.put("msg", code_message);
		if(code.equals(SUCCEED_CODE)){
			resultMap.put("success", true);
		}

		/*
		 * 返回信息结果集合,如果是集合默认key值为'list' {'list' : list集合}
		 */
		getResultData(resultMap, objectData);

		logger.debug(JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue));
		// logger.debug("返回结果json格式："+JSON.toJSONString(map,
		// SerializerFeature.WriteMapNullValue));
		return resultMap;
	}

	/**
	 * 请求成功返回信息结果集合,如果是集合默认key值为'list' {'list' : list集合}
	 * 
	 * @param result
	 * @param errorCode
	 * @param data
	 * @return
	 */
	private static Map getResultData(Map resultMap, Object objectData) {
		if (objectData == null) {
			return resultMap;
		}

		if (objectData instanceof PageInfo) {
			pagehandler(resultMap, (PageInfo) objectData);
		} else if (objectData instanceof JSONObject) {
			// 实体对象都继承自JSONObject，而JSONObject 实现了map接口，所以能这么写
			resultMap.put("data", (Map) objectData);
		} else {
			resultMap.put("data", objectData);
		}

		return resultMap;
	}

	private static void pagehandler(Map map, PageInfo page) {

		 map.put("total", page.getTotal()); //重新定义分页数据key
		 map.put("rows", page.getList());	// easyui 分页数据
//		 map.put("list", page.getList());

	}

	public static void main(String args[]) {
//		PageInfo page = new PageInfo(new ArrayList());
//
//		System.out.println(getResult("200", page));
		System.out.println(ResultUtil.getSuccessResult("asdfjkal;sdjfkl;"));
	}
}
