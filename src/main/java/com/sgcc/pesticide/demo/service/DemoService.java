package com.sgcc.pesticide.demo.service;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.demo.model.Demo;

import java.util.List;
import java.util.Map;

public interface DemoService {
	public Query queryDemoList(Map<String, String> param);
}
