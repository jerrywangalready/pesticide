package com.sgcc.pesticide.getJar.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2019-01-23.
 */
@Service
public class GetJarServiceImpl implements GetJarService {

    @Override
    public List<Map<String, String>> getJarList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("jarName", "cepri_cs"+i+".jar");
            map.put("jarDate", "2019/01/23 12:"+i);
            list.add(map);
        }
        return list;
    }
}
