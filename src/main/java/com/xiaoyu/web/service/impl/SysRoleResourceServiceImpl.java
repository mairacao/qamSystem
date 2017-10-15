package com.xiaoyu.web.service.impl;

import com.xiaoyu.web.model.SysRoleResource;
import com.xiaoyu.web.service.SysRoleResourceService;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysRoleResourceMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
public class SysRoleResourceServiceImpl extends AbstractService<SysRoleResource> implements SysRoleResourceService {
    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

}
