package com.xiaoyu.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.web.model.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.ResultUtil;

import com.xiaoyu.web.model.QaProperty;
import com.xiaoyu.web.service.QaPropertyService;

/**
* Created by Administrator on 2017-10-10.
*/
@RestController
@RequestMapping("/qa/property")
public class QaPropertyController {
    @Resource
    private QaPropertyService qaPropertyService;

    /**
     * 用户管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/user/user";
    }

    /**
     * 特性管理列表
     *
     * @param qaProperty
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
//    @PostMapping("/dataGrid")
//    @ResponseBody
//    public Object dataGrid(PropertyVo propertyVo, @RequestParam Map params) {
//        Map<String, Object> condition = new HashMap<String, Object>();
//
//        if (PMSUtils.isNotEmpty(propertyVo.getName())) {
//            condition.put("name", propertyVo.getName());
//        }
//        if (propertyVo.getOrganizationId() != null) {
//            condition.put("organizationId", propertyVo.getOrganizationId());
//        }
//        if (propertyVo.getCreatedateStart() != null) {
//            condition.put("startTime", propertyVo.getCreatedateStart());
//        }
//        if (propertyVo.getCreatedateEnd() != null) {
//            condition.put("endTime", propertyVo.getCreatedateEnd());
//        }
//
//        params.putAll(condition);
//        PageInfo pageInfo = qaPropertyService.selectPage(params);
//        return ResultUtil.getSuccessResult(pageInfo);
//    }

    @PostMapping("/add")
    public Object add(QaProperty qaProperty) {
        qaPropertyService.save(qaProperty);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam Long propertyId) {
        qaPropertyService.deleteByPrimaryKey(propertyId);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/update")
    public Object update(QaProperty qaProperty) {
        qaPropertyService.updateByPrimaryKey(qaProperty);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/detail")
    public Object detail(@RequestParam Long propertyId) {
        QaProperty qaProperty = qaPropertyService.findById(propertyId);
        return ResultUtil.getSuccessResult(qaProperty);

    }

    @PostMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QaProperty> list = qaPropertyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.getSuccessResult(pageInfo);
    }
}
