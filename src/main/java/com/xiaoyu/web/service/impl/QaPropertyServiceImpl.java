package com.xiaoyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaoyu.commons.result.Tree;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyu.commons.utils.PMSUtils;
import com.xiaoyu.web.core.AbstractService;
import com.xiaoyu.web.mapper.QaPropertyMapper;
import com.xiaoyu.web.model.QaProperty;
import com.xiaoyu.web.service.QaPropertyService;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class QaPropertyServiceImpl extends AbstractService<QaProperty> implements QaPropertyService {

	@Resource
	private QaPropertyMapper qaPropertyMapper;

	public List<QaProperty> selectAll() {

		return qaPropertyMapper.selectAll();

	}

	@Override
	public PageInfo selectPage(Map params) {

		PageHelper.startPage(params);
		if(params.containsKey("sort")){
//			PageHelper.orderBy(PMSUtils.isNull(params.get("sort")) + " " + PMSUtils.isNull(params.get("order")));
			String sortName = PMSUtils.isNull(params.get("sort"));
			//例如：propertyId转化为：property_id
			sortName = PMSUtils.humpToLine(sortName);
			PageHelper.orderBy(sortName + " " + PMSUtils.isNull(params.get("order")));
		}
		List list = qaPropertyMapper.selectAll();

		return new PageInfo(list);
	}

	@Override
	public Object selectTree() {
		List<Tree> trees = new ArrayList<Tree>();
		List<QaProperty> properties = this.selectAll();
		for (QaProperty property : properties) {
			Tree tree = new Tree();
			tree.setId(property.getPropertyId());
			tree.setText(property.getPropertyName());

			trees.add(tree);
		}
		return trees;
	}

}
