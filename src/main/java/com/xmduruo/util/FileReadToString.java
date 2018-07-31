package com.xmduruo.util;

import java.io.*;

/**
 * Created by @Author tachai
 * date 2018/7/28 14:11
 *
 * @Email 1206966083@qq.com
 */
public class FileReadToString {

    public static String toString(String fileName){
        String path = PropertiesUtil.getProperty("jsontemplate.path")+fileName+".json";
        BufferedReader reader = null;
        String laststr = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine())!=null){
                laststr += tempString;
            }
//            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
