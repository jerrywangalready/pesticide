package com.sgcc.pesticide.demo.dao;


import com.sgcc.pesticide.demo.model.Demo;

import java.util.List;
import java.util.Map;

public interface DemoDao {

    public List<Demo> queryDemoList(Map<String, String> param);

}
