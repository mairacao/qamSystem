package com.xiaoyu.web.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.SysUser;
import com.xiaoyu.web.model.vo.UserVo;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface SysUserService extends Service<SysUser> {

	List<SysUser> selectByLoginName(UserVo userVo);

	void insertByVo(UserVo userVo);

	UserVo selectVoById(Long id);

	void updateByVo(UserVo userVo);

	void updatePwdByUserId(Long userId, String md5Hex);

	PageInfo selectPage(Map map);

	void deleteUserById(Long id);
}
