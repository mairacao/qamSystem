package com.xiaoyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoyu.commons.result.Tree;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.SysOrganizationMapper;
import com.xiaoyu.web.model.SysOrganization;
import com.xiaoyu.web.model.SysResource;
import com.xiaoyu.web.service.SysOrganizationService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
public class SysOrganizationServiceImpl extends AbstractService<SysOrganization> implements SysOrganizationService {
	@Resource
	private SysOrganizationMapper sysOrganizationMapper;

	@Override
	public List<Tree> selectTree() {
		List<SysOrganization> organizationList = selectTreeGrid();

		List<Tree> trees = new ArrayList<Tree>();
		if (organizationList != null) {
			for (SysOrganization organization : organizationList) {
				Tree tree = new Tree();
				tree.setId(organization.getId());
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIcon());
				tree.setPid(organization.getPid());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<SysOrganization> selectTreeGrid() {
		Example example = new Example(SysOrganization.class);
		example.orderBy("seq");
		return sysOrganizationMapper.selectByExample(example);
	}

}
