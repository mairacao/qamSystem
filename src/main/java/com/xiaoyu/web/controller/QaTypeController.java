package com.xiaoyu.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.xiaoyu.commons.utils.EasyuiUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.ResultUtil;

import com.xiaoyu.web.model.QaType;
import com.xiaoyu.web.service.QaTypeService;

/**
* Created by Administrator on 2017-10-10.
*/
@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping("/type")
public class QaTypeController {

    @Resource
    private QaTypeService qaTypeService;

    /**
     * 类别管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "qa/type/type";
    }

    /**
     * 类别列表
     *
     * @param params
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(@RequestParam Map params) {

        EasyuiUtils.getParams(params);

        PageInfo pageInfo = qaTypeService.selectPage(params);

        return ResultUtil.getSuccessResult(pageInfo);
    }

    /**
     * 类别树
     *
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        return qaTypeService.selectTree();
    }

    /**
     * 添加类别页
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "qa/type/typeAdd";
    }

    /**
     * 编辑类别页
     *
     * @param model
     * @param typeId
     * @return
     * (Model model,@RequestParam(value="typeId", required=true) Long typeId)
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long typeId) {
        QaType type = qaTypeService.findById(typeId);
        model.addAttribute("type", type);
        return "qa/type/typeEdit";
    }

    @PostMapping("/add")
    @ResponseBody
    public Object add(QaType qaType) {
        qaType.setCreateDate(new Date());
        qaType.setUpdateDate(new Date());

//        Subject user = SecurityUtils.getSubject();
//        qaType.setUpdateUserid(Long.parseLong(user.getSession().getId().toString()));

        qaType.setCreateUserid(Long.parseLong("1"));
        qaType.setUpdateUserid(Long.parseLong("1"));

        qaTypeService.save(qaType);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Long typeId) {
        qaTypeService.deleteByPrimaryKey(typeId);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/edit")
    @ResponseBody
    public Object update(QaType qaType) {
        qaType.setUpdateDate(new Date());
        qaType.setUpdateUserid(Long.parseLong("1"));
        qaTypeService.updateByPrimaryKey(qaType);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/detail")
    public Object detail(@RequestParam Long typeId) {
        QaType qaType = qaTypeService.findById(typeId);
        return ResultUtil.getSuccessResult(qaType);

    }

    @PostMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QaType> list = qaTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.getSuccessResult(pageInfo);
    }
}
