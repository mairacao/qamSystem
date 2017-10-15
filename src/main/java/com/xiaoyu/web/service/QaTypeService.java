package com.xiaoyu.web.service;

import com.github.pagehelper.PageInfo;

import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.QaType;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-10.
 */
public interface QaTypeService extends Service<QaType> {

    PageInfo selectPage(Map params);

    Object selectTree();
}
