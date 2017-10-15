package com.xiaoyu.web.service;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.SysRole;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface SysRoleService extends Service<SysRole> {
	
    PageInfo selectPage(Map params);

    Object selectTree();

    List<Long> selectResourceIdListByRoleId(Long id);

    void updateRoleResource(Long id, String resourceIds);

    Map<String, Set<String>> selectResourceMapByUserId(Long userId);

}
