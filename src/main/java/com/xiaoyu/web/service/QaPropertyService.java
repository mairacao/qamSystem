package com.xiaoyu.web.service;

import com.github.pagehelper.PageInfo;

import com.xiaoyu.web.core.Service;
import com.xiaoyu.web.model.QaProperty;

import java.util.Map;

/**
 * Created by Administrator on 2017-10-10.
 */
public interface QaPropertyService extends Service<QaProperty> {

    PageInfo selectPage(Map params);

    Object selectTree();

}
