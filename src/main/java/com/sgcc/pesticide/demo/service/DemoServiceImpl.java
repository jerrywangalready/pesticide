package com.sgcc.pesticide.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sgcc.pesticide.demo.dao.DemoDao;
import com.sgcc.pesticide.demo.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	DemoDao demoDao;

	@Override
	public PageInfo queryDemoList(Map<String, String> param){

		PageHelper.startPage(Integer.parseInt(param.get("pageNum")),Integer.parseInt(param.get("pageSize")));
		List<Demo> list = demoDao.queryDemoList(param);
		return new PageInfo(list);
	}

}
