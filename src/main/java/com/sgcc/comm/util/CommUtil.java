package com.sgcc.comm.util;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author jerrywang
 * @create 2017/1/26.
 */
public class CommUtil {

    private static CommUtil instance;

    public static CommUtil getInstance() {
        if (instance == null) {
            instance = new CommUtil();
        }
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
     * @Description 将图片流转出图片并存储
     * @author JerryWang
     * @date 2017/1/26 18:56
     * @param baseStr
     * @param path
     * @return uuid
     */
    public String saveImage(String baseStr, String path){

        BASE64Decoder decoder = new BASE64Decoder();
        String uuid = CommUtil.getUUID();
        try {
            File dirfile = new File(path);
            // 判断路径是否存在,不存在则创建
            if (!dirfile.exists() || !dirfile.isDirectory()) {
                dirfile.mkdir();
            }

            //Base64解码
            byte[] b = decoder.decodeBuffer(baseStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = path + uuid + ".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "/upload/"+uuid+".png";
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

    public static LoginInfo getLoginInfo(){
        LoginInfo li = new LoginInfo();
        return li;
    }

    public static class LoginInfo{

        private static String login_user;

        public static String getLogin_user() {
            return login_user;
        }

        public static void setLogin_user(String login_user) {
            LoginInfo.login_user = login_user;
        }
    }

}
