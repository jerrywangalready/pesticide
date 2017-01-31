package com.sgcc.pesticide.index.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/22.
 */
public interface IndexService {

    public List<Map<String, String>> getObjectsByUser(String username);
}
