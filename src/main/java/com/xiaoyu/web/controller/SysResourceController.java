package com.xiaoyu.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoyu.commons.base.BaseController;
import com.xiaoyu.commons.shiro.ShiroUser;
import com.xiaoyu.commons.utils.ResultUtil;
import com.xiaoyu.web.model.SysResource;
import com.xiaoyu.web.service.SysResourceService;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Controller
@RequestMapping("/resource")
public class SysResourceController extends BaseController {
	@Resource
	private SysResourceService sysResourceService;

	/**
	 * 菜单树
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		ShiroUser shiroUser = getShiroUser();
		return sysResourceService.selectTree(shiroUser);
	}

	/**
	 * 资源管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/resource/resource";
	}

	/**
	 * 资源管理列表
	 *
	 * @return
	 */
	@PostMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return sysResourceService.selectAll();
	}

	/**
	 * 添加资源页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/resource/resourceAdd";
	}

	/**
	 * 添加资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid SysResource resource) {
		resource.setCreateTime(new Date());
		// 选择菜单时将openMode设置为null
		Integer type = resource.getResourceType();
		if (null != type && type == 0) {
			resource.setOpenMode(null);
		}
		sysResourceService.save(resource);
		return ResultUtil.getSuccessResult("添加成功！");
	}

	/**
	 * 查询所有的菜单
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public Object allMenu() {
		return sysResourceService.selectAllMenu();
	}

	/**
	 * 查询所有的资源tree
	 */
	@RequestMapping("/allTrees")
	@ResponseBody
	public Object allTree() {
		return sysResourceService.selectAllTree();
	}

	/**
	 * 编辑资源页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, Long id) {
		SysResource resource = sysResourceService.findById(id);
		model.addAttribute("resource", resource);
		return "admin/resource/resourceEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Object update(@Valid SysResource sysResource) {
		sysResourceService.updateByPrimaryKey(sysResource);
		return ResultUtil.getSuccessResult("修改成功！");
	}

	@PostMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam Long id) {
		sysResourceService.deleteByPrimaryKey(id);
		return ResultUtil.getSuccessResult("删除成功！");
	}

}
