package com.xiaoyu.web.service;

import com.xiaoyu.commons.base.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.web.mapper.QaProcessProductMapper;
import com.xiaoyu.web.model.QaProcessProduct;

/**
 * Created by Administrator on 2017-10-10.
 */
 
@Service
public class QaProcessProductService extends MybatisService<QaProcessProduct> {

    @Autowired
    private QaProcessProductMapper qaProcessProductMapper;
}
