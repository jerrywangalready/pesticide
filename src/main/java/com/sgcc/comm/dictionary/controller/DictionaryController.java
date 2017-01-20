package com.sgcc.comm.dictionary.controller;

import com.sgcc.comm.dictionary.service.DictionaryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/19.
 */
@Controller
@RequestMapping("/dict")
public class DictionaryController {

    static Logger logger = LogManager.getLogger(DictionaryController.class.getName());

    @Autowired
    DictionaryService dictionaryService;

    @ResponseBody
    @RequestMapping(value = "/getDict", method = RequestMethod.POST)
    public List<Map<String, String>> getDict(@RequestBody Map<String, String> param){
        return dictionaryService.getDict(param);
    }
    @ResponseBody
    @RequestMapping(value = "/getDictByArray", method = RequestMethod.POST)
    public Map<String, Map> getDict(@RequestBody Map<String, String>[] paramArray){
        return dictionaryService.getDict(paramArray);
    }
}
