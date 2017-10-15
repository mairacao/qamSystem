package com.xiaoyu.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiaoyu.web.model.SysUser;
import com.xiaoyu.web.model.vo.UserVo;

import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

	UserVo selectUserVoById(@Param("id") Long id);

	List selectUserPage(Map params);
}