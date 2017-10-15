package com.xiaoyu.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiaoyu.web.model.SysResource;
import com.xiaoyu.web.model.SysRole;
import com.xiaoyu.web.model.SysUserRole;

import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends Mapper<SysRole> {
	
    List<Long> selectResourceIdListByRoleId(@Param("id") Long id);

    List<SysResource> selectResourceListByRoleIdList(@Param("list") List<SysUserRole> list);

    List<Map<Long, String>> selectResourceListByRoleId(@Param("id") Long id);
}