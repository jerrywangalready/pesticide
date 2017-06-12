package com.sgcc.pesticide.imageTest.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.controller.ImageUploadUtil;
import com.sgcc.pesticide.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageTestController {

    @RequestMapping("/init")
    public String init(){
        return "/imageTest/imageTest";
    }

    /**
     * @Description
     * @author 杜成皓
     * @date 2017/1/21 22:26
     * @return
     */
    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(String command, String type, String responseType, HttpServletRequest request){
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("upload");
        String uuid = CommUtil.getUUID();
        String path = CommUtil.getInstance().PROPERTIES.get("upload-path");
        try {
            File uploadFile = new File(path + uuid + ".png");
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uuid);
        return "{\"uploaded\":1,\"fileName\":\""+uuid+".png\",\"url\":\"/upload/"+uuid+".png\"}";
    }

}
