package com.xiaoyu.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.ResultUtil;
import com.xiaoyu.web.model.SysOrganization;
import com.xiaoyu.web.service.SysOrganizationService;

/**
* Created by CodeGenerator on 2017/07/20.
*/
@Controller
@RequestMapping("/organization")
public class SysOrganizationController {
    @Resource
    private SysOrganizationService sysOrganizationService;

    
    /**
     * 部门管理主页
     *
     * @return
     */
    @GetMapping(value = "/manager")
    public String manager() {
        return "admin/organization/organization";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public Object tree() {
        return sysOrganizationService.selectTree();
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return sysOrganizationService.selectTreeGrid();
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/organization/organizationAdd";
    }
    
    
    
    @PostMapping("/add")
    @ResponseBody
    public Object add(SysOrganization sysOrganization) {
    	sysOrganization.setCreateTime(new Date());
        sysOrganizationService.save(sysOrganization);
        return ResultUtil.getSuccessResult();
    }

    /**
     * 编辑部门页
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
    	SysOrganization organization = sysOrganizationService.findById(id);
        model.addAttribute("organization", organization);
        return "admin/organization/organizationEdit";
    }
    
    @PostMapping("/edit")
    @ResponseBody
    public Object update(SysOrganization sysOrganization) {
        sysOrganizationService.updateByPrimaryKey(sysOrganization);
        return ResultUtil.getSuccessResult();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        sysOrganizationService.deleteByPrimaryKey(id);
        return ResultUtil.getSuccessResult();
    }
    
    @PostMapping("/detail")
    @ResponseBody
    public Object detail(@RequestParam Long id) {
        SysOrganization sysOrganization = sysOrganizationService.findById(id);
        return ResultUtil.getSuccessResult(sysOrganization);
        
    }

    @PostMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysOrganization> list = sysOrganizationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.getSuccessResult(pageInfo);
    }
}
