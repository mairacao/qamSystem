package com.xiaoyu.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.EasyuiUtils;
import com.xiaoyu.commons.utils.ResultUtil;
import com.xiaoyu.web.model.SysUser;
import com.xiaoyu.web.service.SysLogService;

/**
 * Created by CodeGenerator on 2017/07/20.
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

	@Resource
	private SysLogService sysLogService;

	@GetMapping("/manager")
	public String manager() {
		return "admin/syslog";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(@RequestParam Map params) {
		EasyuiUtils.getParams(params);
		PageInfo pageInfo = sysLogService.selectPage(params);
		

		SysUser user = new SysUser();
		if(user.getName().equals("1")){
			
		}
		
		return ResultUtil.getSuccessResult(pageInfo);
	}
}
