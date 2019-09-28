package com.sgcc.pesticide.demo.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface DemoService {
	PageInfo queryDemoList(Map<String, String> param);

}
