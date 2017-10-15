package com.xiaoyu.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.BeanUtils;
import com.xiaoyu.commons.utils.EasyuiUtils;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.commons.utils.StringUtils;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysUserMapper;
import com.xiaoyu.web.mapper.SysUserRoleMapper;
import com.xiaoyu.web.model.SysUser;
import com.xiaoyu.web.model.SysUserRole;
import com.xiaoyu.web.model.vo.UserVo;
import com.xiaoyu.web.service.SysUserService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
	
	@Resource
	private SysUserMapper sysUserMapper;

	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUser> selectByLoginName(UserVo userVo) {
		SysUser user = new SysUser();
		user.setLoginName(userVo.getLoginName());;
		return sysUserMapper.select(user);
		
//		Example example = new Example(SysUser.class);
//		example.createCriteria().andNotEqualTo("id", userVo.getId());
//
//		return sysUserMapper.selectByExample(example);

	}

	@Override
	public void insertByVo(UserVo userVo) {
		SysUser user = BeanUtils.copy(userVo, SysUser.class);
		user.setCreateTime(new Date());

		this.save(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		SysUserRole userRole = new SysUserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			sysUserRoleMapper.insertSelective(userRole);
		}
	}

	@Override
	public UserVo selectVoById(Long id) {
		return sysUserMapper.selectUserVoById(id);
	}

	@Override
	public void updateByVo(UserVo userVo) {
		SysUser user = BeanUtils.copy(userVo, SysUser.class);
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		this.updateByPrimaryKey(user);

		Long id = userVo.getId();
		List<SysUserRole> userRoles = sysUserRoleMapper.selectByUserId(id);
		if (userRoles != null && !userRoles.isEmpty()) {
			for (SysUserRole userRole : userRoles) {
				sysUserRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		SysUserRole userRole = new SysUserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));

			sysUserRoleMapper.insertSelective(userRole);
		}
	}

	@Override
	public void updatePwdByUserId(Long userId, String md5Hex) {
		SysUser user = new SysUser();
		user.setId(userId);
		user.setPassword(md5Hex);

		this.updateByPrimaryKey(user);
	}

	@Override
	public PageInfo selectPage(Map params) {
		EasyuiUtils.getParams(params);
		PageHelper.startPage(params);
		if(params.containsKey("sort")){
			PageHelper.orderBy(PMSUtils.isNull(params.get("sort")) + " " + PMSUtils.isNull(params.get("order")));
		}
		List list = sysUserMapper.selectUserPage(params);
		return new PageInfo(list);

	}

	@Override
	public void deleteUserById(Long id) {
		this.deleteByPrimaryKey(id);

		// 删除用户角色关系表
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(id);
		sysUserRoleMapper.delete(userRole);
	}

}
