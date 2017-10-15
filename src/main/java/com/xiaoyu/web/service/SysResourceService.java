package com.xiaoyu.web.service;
import java.util.List;

import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.commons.shiro.ShiroUser;
import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.SysResource;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface SysResourceService extends Service<SysResource> {
	
    List<SysResource> selectAll();

    /**
     * 查询所有的菜单
     * @return
     */
    List<Tree> selectAllMenu();

    /**
     * 查询整个资源树（菜单、按钮）
     * @return
     */
    List<Tree> selectAllTree();

    /**
     * 根据用户获取资源树（菜单、按钮）
     * @param shiroUser
     * @return
     */
    List<Tree> selectTree(ShiroUser shiroUser);

}
