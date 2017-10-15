package com.xiaoyu.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysUserRoleMapper;
import com.xiaoyu.web.model.SysUserRole;
import com.xiaoyu.web.service.SysUserRoleService;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

}
