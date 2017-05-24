package com.sgcc.pesticide.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
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
	public Query queryDemoList(Map<String, String> param){

		PageHelper.startPage(Integer.parseInt(param.get("pageNum")),Integer.parseInt(param.get("pageSize")));
		List<Demo> list = demoDao.queryDemoList(param);
		Query query = new Query();
		query.setList(list);
		query.setPageNum(Integer.parseInt(param.get("pageNum")));
		query.setPageSize(Integer.parseInt(param.get("pageSize")));
		query.setTotal(((Page)list).getTotal());

		return query;
	}

}
