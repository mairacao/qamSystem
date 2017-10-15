package com.xiaoyu.web.service;
import com.xiaoyu.web.model.SysLog;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.web.core.Service;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
public interface SysLogService extends Service<SysLog> {

    PageInfo selectPage(Map params);
}
