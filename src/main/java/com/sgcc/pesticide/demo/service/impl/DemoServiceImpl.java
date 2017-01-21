package com.sgcc.pesticide.demo.service.impl;

import com.sgcc.pesticide.demo.dao.DemoDao;
import com.sgcc.pesticide.demo.model.Demo;
import com.sgcc.pesticide.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	DemoDao demoDao;
	public List<Demo> queryDemoList(){
		List<Demo> list = demoDao.queryDemoList();
		return list;

	}

}
