package com.xiaoyu.web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.commons.shiro.ShiroUser;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysResourceMapper;
import com.xiaoyu.web.mapper.SysRoleMapper;
import com.xiaoyu.web.mapper.SysRoleResourceMapper;
import com.xiaoyu.web.mapper.SysUserRoleMapper;
import com.xiaoyu.web.model.SysResource;
import com.xiaoyu.web.model.SysUserRole;
import com.xiaoyu.web.service.SysResourceService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
public class SysResourceServiceImpl extends AbstractService<SysResource> implements SysResourceService {

	@Resource
	private SysResourceMapper sysResourceMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleResourceMapper sysRoleResourceMapper;

	private static final int RESOURCE_MENU = 0; // 菜单

	@Override
	public List<SysResource> selectAll() {
		Example example = new Example(SysResource.class);
		example.orderBy("seq");
		return sysResourceMapper.selectByExample(example);
	}

	public List<SysResource> selectByType(Integer type) {

		Example example = new Example(SysResource.class);
		example.createCriteria().andEqualTo("resourceType", type);
		example.orderBy("seq");
		return sysResourceMapper.selectByExample(example);
	}

	@Override
	public List<Tree> selectAllMenu() {
		List<Tree> trees = new ArrayList<Tree>();
		// 查询所有菜单
		List<SysResource> resources = this.selectByType(RESOURCE_MENU);
		if (resources.size() == 0) {
			return trees;
		}
		for (SysResource resource : resources) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Tree> selectAllTree() {
		// 获取所有的资源 tree形式，展示
		List<Tree> trees = new ArrayList<Tree>();
		List<SysResource> resources = this.selectAll();
		if (resources == null) {
			return trees;
		}
		for (SysResource resource : resources) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Tree> selectTree(ShiroUser shiroUser) {
		List<Tree> trees = new ArrayList<Tree>();
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		if (roles == null) {
			return trees;
		}
		// 如果有超级管理员权限
		if (roles.contains("admin")) {
			List<SysResource> resourceList = this.selectByType(RESOURCE_MENU);
			if (resourceList == null) {
				return trees;
			}
			for (SysResource resource : resourceList) {
				Tree tree = new Tree();
				tree.setId(resource.getId());
				tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());
				trees.add(tree);
			}
			return trees;
		}
		// 普通用户
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(shiroUser.getId());

		List<SysUserRole> userRoleList = sysUserRoleMapper.select(userRole);
		if (userRoleList.size() == 0) {
			return trees;
		}
		List<SysResource> resourceLists = sysRoleMapper.selectResourceListByRoleIdList(userRoleList);
		if (resourceLists == null) {
			return trees;
		}
		for (SysResource resource : resourceLists) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setOpenMode(resource.getOpenMode());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

}
