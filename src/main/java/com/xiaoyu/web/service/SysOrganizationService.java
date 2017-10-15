package com.xiaoyu.web.service;
import java.util.List;

import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.SysOrganization;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface SysOrganizationService extends Service<SysOrganization> {
	
    List<Tree> selectTree();

    List<SysOrganization> selectTreeGrid();


}
