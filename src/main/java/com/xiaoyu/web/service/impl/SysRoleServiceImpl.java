package com.xiaoyu.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysRoleMapper;
import com.xiaoyu.web.mapper.SysRoleResourceMapper;
import com.xiaoyu.web.mapper.SysUserRoleMapper;
import com.xiaoyu.web.model.SysRole;
import com.xiaoyu.web.model.SysRoleResource;
import com.xiaoyu.web.model.SysUserRole;
import com.xiaoyu.web.service.SysRoleService;

import tk.mybatis.mapper.entity.Example;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;
    
    public List<SysRole> selectAll() {
        
        return sysRoleMapper.selectAll();
        
    }
    
	@Override
    public PageInfo selectPage(Map params) {
    	
        PageHelper.startPage(params);
		if(params.containsKey("sort")){
			PageHelper.orderBy(PMSUtils.isNull(params.get("sort")) + " " + PMSUtils.isNull(params.get("order")));
		}
        List list = sysRoleMapper.selectAll();
        
        return new PageInfo(list);
    }

    @Override
    public Object selectTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<SysRole> roles = this.selectAll();
        for (SysRole role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public void updateRoleResource(Long roleId, String resourceIds) {
        // 先删除后添加,有点爆力
        SysRoleResource roleResource = new SysRoleResource();
        roleResource.setRoleId(roleId);
        sysRoleResourceMapper.delete(roleResource);
        
        
        String[] resourceIdArray = resourceIds.split(",");
        for (String resourceId : resourceIdArray) {
            roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Long.parseLong(resourceId));
            sysRoleResourceMapper.insertSelective(roleResource);
        }
    }

    @Override
    public List<Long> selectResourceIdListByRoleId(Long id) {
        return sysRoleMapper.selectResourceIdListByRoleId(id);
    }
    
    @Override
    public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
        
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);;
        List<SysUserRole> userRolelist = sysUserRoleMapper.select(userRole);
        
        Set<String> urlSet = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        for (SysUserRole sysUserRole : userRolelist) {
        	
        	 List<Map<Long, String>> resourceList = sysRoleMapper.selectResourceListByRoleId(sysUserRole.getRoleId());
             if (resourceList != null) {
                 for (Map<Long, String> map : resourceList) {
                     if (PMSUtils.isNotEmpty(map.get("url"))) {
                         urlSet.add(map.get("url"));
                     }
                 }
             }
             SysRole role = sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId());
             if (role != null) {
                 roles.add(role.getName());
             }
		}
        
        resourceMap.put("urls", urlSet);
        resourceMap.put("roles", roles);
        return resourceMap;
    }

}
