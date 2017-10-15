package com.xiaoyu.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.EasyuiUtils;
import com.xiaoyu.commons.utils.ResultUtil;
import com.xiaoyu.web.model.SysRole;
import com.xiaoyu.web.service.SysRoleService;

/**
 * Created by CodeGenerator on 2017/07/20.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 权限管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/role/role";
	}

	/**
	 * 权限列表
	 *
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(@RequestParam Map params) {
		
		EasyuiUtils.getParams(params);
		
		PageInfo pageInfo = sysRoleService.selectPage(params);
		
		return ResultUtil.getSuccessResult(pageInfo);
	}

	/**
	 * 权限树
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		return sysRoleService.selectTree();
	}

	/**
	 * 添加权限页
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/role/roleAdd";
	}
	
	 /**
     * 编辑权限页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        SysRole role = sysRoleService.findById(id);
        model.addAttribute("role", role);
        return "admin/role/roleEdit";
    }
    

	@PostMapping("/add")
	@ResponseBody
	public Object add(SysRole sysRole) {
		sysRoleService.save(sysRole);
		return ResultUtil.getSuccessResult();
	}

	@PostMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam Long id) {
		sysRoleService.deleteByPrimaryKey(id);
		return ResultUtil.getSuccessResult();
	}

	@PostMapping("/edit")
	@ResponseBody
	public Object update(SysRole sysRole) {
		sysRoleService.updateByPrimaryKey(sysRole);
		return ResultUtil.getSuccessResult();
	}

	@PostMapping("/detail")
	public Object detail(@RequestParam Long id) {
		SysRole sysRole = sysRoleService.findById(id);
		return ResultUtil.getSuccessResult(sysRole);

	}

    /**
     * 授权页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/grantPage")
    public String grantPage(Model model, Long id) {
        model.addAttribute("id", id);
        return "admin/role/roleGrant";
    }
    
    /**
     * 授权页面页面根据角色查询资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Object findResourceByRoleId(Long id) {
        List<Long> resources = sysRoleService.selectResourceIdListByRoleId(id);
        return ResultUtil.getSuccessResult(resources);
    }
    
    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping("/grant")
    @ResponseBody
    public Object grant(Long id, String resourceIds) {
    	sysRoleService.updateRoleResource(id, resourceIds);
        return ResultUtil.getSuccessResult("授权成功！");
    }
}
