package com.xiaoyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.QaTypeMapper;
import com.xiaoyu.web.model.QaType;
import com.xiaoyu.web.service.QaTypeService;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class QaTypeServiceImpl extends AbstractService<QaType> implements QaTypeService {
    @Resource
    private QaTypeMapper qaTypeMapper;

    
    public List<QaType> selectAll() {
        
        return qaTypeMapper.selectAll();
        
    }
    
	@Override
    public PageInfo selectPage(Map params) {
    	
        PageHelper.startPage(params);
		if(params.containsKey("sort")){
//			PageHelper.orderBy(PMSUtils.isNull(params.get("sort")) + " " + PMSUtils.isNull(params.get("order")));
            String sortName = PMSUtils.isNull(params.get("sort"));
            sortName = PMSUtils.humpToLine(sortName);
            PageHelper.orderBy(sortName + " " + PMSUtils.isNull(params.get("order")));
		}
        List list = qaTypeMapper.selectAll();
        
        return new PageInfo(list);
    }

    @Override
    public Object selectTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<QaType> types = this.selectAll();
        for (QaType type : types) {
            Tree tree = new Tree();
            tree.setId(type.getTypeId());
            tree.setText(type.getTypeName());

            trees.add(tree);
        }
        return trees;
    }

//    @Override
//    public void updateRoleResource(Long roleId, String resourceIds) {
//        // 先删除后添加,有点爆力
//        SysRoleResource roleResource = new SysRoleResource();
//        roleResource.setRoleId(roleId);
//        sysRoleResourceMapper.delete(roleResource);
//
//
//        String[] resourceIdArray = resourceIds.split(",");
//        for (String resourceId : resourceIdArray) {
//            roleResource = new SysRoleResource();
//            roleResource.setRoleId(roleId);
//            roleResource.setResourceId(Long.parseLong(resourceId));
//            sysRoleResourceMapper.insertSelective(roleResource);
//        }
//    }

//    @Override
//    public List<Long> selectResourceIdListByRoleId(Long id) {
//        return sysRoleMapper.selectResourceIdListByRoleId(id);
//    }
    
//    @Override
//    public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
//        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
//
//        SysUserRole userRole = new SysUserRole();
//        userRole.setUserId(userId);;
//        List<SysUserRole> userRolelist = sysUserRoleMapper.select(userRole);
//
//        Set<String> urlSet = new HashSet<String>();
//        Set<String> roles = new HashSet<String>();
//        for (SysUserRole sysUserRole : userRolelist) {
//
//        	 List<Map<Long, String>> resourceList = sysRoleMapper.selectResourceListByRoleId(sysUserRole.getRoleId());
//             if (resourceList != null) {
//                 for (Map<Long, String> map : resourceList) {
//                     if (PMSUtils.isNotEmpty(map.get("url"))) {
//                         urlSet.add(map.get("url"));
//                     }
//                 }
//             }
//             SysRole role = sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId());
//             if (role != null) {
//                 roles.add(role.getName());
//             }
//		}
//
//        resourceMap.put("urls", urlSet);
//        resourceMap.put("roles", roles);
//        return resourceMap;
//    }

}
