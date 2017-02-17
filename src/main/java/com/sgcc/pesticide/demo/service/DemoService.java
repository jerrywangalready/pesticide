package com.sgcc.pesticide.demo.service;

import com.sgcc.comm.model.Query;

import java.util.Map;

public interface DemoService {
	Query queryDemoList(Map<String, String> param);
}
