package com.sgcc.comm.util;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author jerrywang
 * @create 2017/1/26.
 */
public class CommUtil {

    private static final CommUtil instance = new CommUtil();

    private CommUtil(){
    }

    public static CommUtil getInstance() {
        return instance;
    }


    /**
     * @Description 获取UUID
     * @author JerryWang
     * @date 2017/1/26 18:55
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * @Description
     * @author JerryWang
     * @date 2017/1/26 19:00
     * @param sPath
     */
    public void createPath(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    /**
     * @Description 获取项目根路径
     * @author JerryWang
     * @date 2017/1/26 19:44
     */
    public static String getRootPath(){
        return Thread.currentThread().getContextClassLoader().getResource("")
                .toString().replace("file:","").replace("classes/","");
    }

    /**
     * @Description 根据config.properties配置文件获取配置信息
     * @author JerryWang
     * @date 2017/1/26 20:08
     * @param name
     */
    public static String getResourceProperty(String name){
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        return bundle.getString(name);
    }

    /**
     * 配置信息
     */
    public static Map<String, String> PROPERTIES = new HashMap<>();

    /**
     * @Description 根据路径删除文件
     * @author JerryWang
     * @date 2017/7/10 23:44
     * @param sPath
     * @return
     */
    public static void deleteFile(String sPath){
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

}
