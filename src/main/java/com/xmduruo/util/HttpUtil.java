package com.xmduruo.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by @Author tachai
 * date 2018/7/12 15:18
 *
 * @Email 1206966083@qq.com
 */
@Slf4j
public class HttpUtil {


    public static  String okhttp(String url,String data){
        String res ="";
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = httpClient.newCall(request).execute();
            res = response.body().string();
        } catch (IOException e) {
            log.error("连接微信发生了异常：{}" ,Class.class.getCanonicalName());
            e.printStackTrace();
        }

        return res;
    }
}
