package com.xiaoyu.web.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.ResultUtil;

import com.xiaoyu.web.model.QaProcessProduct;
import com.xiaoyu.web.service.QaProcessProductService;

/**
* Created by Administrator on 2017-10-10.
*/
@RestController
@RequestMapping("/qa/process/product")
public class QaProcessProductController {
//    @Resource
//    private QaProcessProductService qaProcessProductService;
//
//    @PostMapping("/add")
//    public Object add(QaProcessProduct qaProcessProduct) {
//        qaProcessProductService.save(qaProcessProduct);
//        return ResultUtil.getSuccessResult();
//    }
//
//    @PostMapping("/delete")
//    public Object delete(@RequestParam Long id) {
//        qaProcessProductService.deleteByPrimaryKey(id);
//        return ResultUtil.getSuccessResult();
//    }
//
//    @PostMapping("/update")
//    public Object update(QaProcessProduct qaProcessProduct) {
//        qaProcessProductService.updateByPrimaryKey(qaProcessProduct);
//        return ResultUtil.getSuccessResult();
//    }
//
//    @PostMapping("/detail")
//    public Object detail(@RequestParam Long id) {
//        QaProcessProduct qaProcessProduct = qaProcessProductService.findById(id);
//        return ResultUtil.getSuccessResult(qaProcessProduct);
//
//    }
//
//    @PostMapping("/list")
//    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<QaProcessProduct> list = qaProcessProductService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultUtil.getSuccessResult(pageInfo);
//    }
}
