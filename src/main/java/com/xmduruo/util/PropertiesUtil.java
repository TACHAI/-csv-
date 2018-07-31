package com.xmduruo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by @Author tachai
 * date 2018/7/28 13:02
 *
 * @Email 1206966083@qq.com
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName="arm.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("配置文件读取异常");
            e.printStackTrace();
        }
    }

    public static  String getProperty(String key){
        String value = props.getProperty(key);
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return value.trim();
    }
    public static  String getProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
