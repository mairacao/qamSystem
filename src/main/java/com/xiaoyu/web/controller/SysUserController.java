package com.xiaoyu.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.base.BaseController;
import com.xiaoyu.commons.shiro.PasswordHash;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.commons.utils.ResultUtil;
import com.xiaoyu.web.model.SysRole;
import com.xiaoyu.web.model.SysUser;
import com.xiaoyu.web.model.vo.UserVo;
import com.xiaoyu.web.service.SysUserService;

/**
 * Created by CodeGenerator on 2017/07/20.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Resource
	private SysUserService sysUserService;
    @Autowired
    private PasswordHash passwordHash;

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
	 * 用户管理列表
	 *
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(UserVo userVo, @RequestParam Map params) {
		Map<String, Object> condition = new HashMap<String, Object>();

		if (PMSUtils.isNotEmpty(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}

		params.putAll(condition);
		PageInfo pageInfo = sysUserService.selectPage(params);
		return ResultUtil.getSuccessResult(pageInfo);
	}

	/**
	 * 添加用户页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/user/userAdd";
	}

	@PostMapping("/add")
	@ResponseBody
	public Object add(SysUser sysUser) {
		sysUser.setCreateTime(new Date());
		sysUserService.save(sysUser);
		return ResultUtil.getSuccessResult();
	}

	@PostMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam Long id) {
		sysUserService.deleteByPrimaryKey(id);
		return ResultUtil.getSuccessResult();
	}

	/**
	 * 编辑用户页
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		UserVo userVo = sysUserService.selectVoById(id);
		List<SysRole> rolesList = userVo.getRolesList();
		List<Long> ids = new ArrayList<Long>();
		for (SysRole role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "admin/user/userEdit";
	}

	/**
	 * 编辑用户
	 *
	 * @param userVo
	 * @return
	 */
	@RequiresRoles("admin")
	@PostMapping("/edit")
	@ResponseBody
	public Object update(SysUser sysUser) {
		sysUserService.updateByPrimaryKey(sysUser);
		return ResultUtil.getSuccessResult();
	}
	
	   /**
     * 修改密码页
     *
     * @return
     */
    @GetMapping("/editPwdPage")
    public String editPwdPage() {
        return "admin/user/userEditPwd";
    }
    
    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @PostMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
        SysUser user = sysUserService.findById(getUserId());
        String salt = user.getSalt();
        if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
            return ResultUtil.getErrorResult("老密码不正确!");
        }
        sysUserService.updatePwdByUserId(getUserId(), passwordHash.toHex(pwd, salt));
        return ResultUtil.getSuccessResult("密码修改成功！");
    }
    

}
