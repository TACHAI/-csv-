package com.xmduruo.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xmduruo.dto.Objectjson;
import com.xmduruo.dto.Query;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by @Author tachai
 * date 2018/7/28 13:57
 *
 * @Email 1206966083@qq.com
 */
public class TestJson {

    public static void main(String[] args) {


       Map map = DemoJson.parseTestJson("酒类办理流程");
       List<String> list = (List<String>) map.get("list");

       for(String str:list){
           System.out.println(str);
       }

    }

    public static String test(String result){
        String res = FileReadToString.toString("酒类办理流程");
        String aa =testQuery("我要卖酒");
        JsonParser parser = new JsonParser();//创建json解析器

        JsonObject jsonObject = (JsonObject) parser.parse(res);

        JsonObject jsonObject1 = WeChatParseJson.parseWeChatJson(jsonObject);

        //遍历mach
        List<Objectjson> list = new LinkedList<>();
        List<Objectjson> list1 = WeChatParseJson.diguiJson(list,jsonObject1);

        System.out.println(list1.size());
        for(Objectjson objectjson:list1){
            JsonObject jsonObject2 = objectjson.getJsonObject();
            JsonArray jsonObject3 = jsonObject2.getAsJsonArray("match");

            if(jsonObject3 !=null&&!jsonObject3.toString().equals("[]")){

                if(jsonObject2.get("ask")!=null){
                    String mm = jsonObject2.get("ask").toString();
                    System.out.println(mm);
                }else {
                    System.out.println("这个对象没有ask");
                }
                String temp=null;

                for(int i=0;i<jsonObject3.size();i++){
                     temp = testQuery(jsonObject3.get(i).toString());
                    if("".equals(temp)||temp==null){
                        StringBuffer strTemp = new StringBuffer(result);
                        result = strTemp.append(temp).toString();
                        WeChatParseJson.clearTask("oX5hwwLxeGMcvKcu-Qk9YMsDfpQQ","酒类办理流程");
                       test(result);
                    }
                    System.out.println("jsonObject3"+jsonObject3.toString());
                    System.out.println("对话"+temp);
                }



            } else if(jsonObject3 !=null&&jsonObject3.toString().equals("[]")){
                String resultTB = testQuery("1");
                System.out.println("这里应该是填表"+resultTB);
            }
        }
        return result;
    }


    /**
     * 根据测试用例来测试
     * @param name
     * @param str
     * @return
     */
    public static  List<String> testByTestCase(String name ,String[] str){
        //todo 没用到
//        String res = FileReadToString.toString(name);
//
//        JsonParser parser = new JsonParser();//创建json解析器
//
//        JsonObject jsonObject = (JsonObject) parser.parse(res);
//
//        JsonObject jsonObject1 = WeChatParseJson.parseWeChatJson(jsonObject);
        //todo 暂时不用
//        List<String[]> matchs= DemoJson.matchAddSuffix(jsonObject1,str);
        String str2="";
        List<String> list= new ArrayList<>();
//        for(int i=0;i<matchs.size();i++){
//            String[] temp = matchs.get(i);
//            for(int j= 0;j<temp.length;j++){
//                str2 = com.xmduruo.util.StringUtils.trim(temp[j], '"');
//            }
//            str1 +=str2;
//        }

        for(int i=0;i<str.length;i++){
            System.out.println(str[i]);
            String str1 ="【"+i+"】"+testQuery(str[i])+"\n";
            System.out.println(str1);
//            String str1 =testQuery(com.xmduruo.util.StringUtils.trim(str[i], '"'))+"\n";
            list.add(str1);
        }

        WeChatParseJson.clearSession("oX5hwwLxeGMcvKcu-Qk9YMsDfpQQ");
        WeChatParseJson.clearTask("oX5hwwLxeGMcvKcu-Qk9YMsDfpQQ",name);
        if(list.size()<str.length){
            list.add("发生了错误");
        }
        return list;

    }



    //发送query信息查询数据
    public static String testQuery(String fileName){

        Query query = new Query();
        query.setQuery(fileName);
        query.setUserId("oX5hwwLxeGMcvKcu-Qk9YMsDfpQQ");
        String data = new Gson().toJson(query);
        String result = HttpUtil.okhttp(PropertiesUtil.getProperty("query.url"),data);
        JsonParser parser = new JsonParser();//创建json解析器
        //得到得到解析的值
        JsonObject jsonObject = (JsonObject) parser.parse(result);
        JsonObject jsonObject1 = (JsonObject) jsonObject.get("info");
        result = jsonObject1.get("ask").toString();
        return  result;
    }


//    //发送query信息查询数据
//    public static String testQuerydev(String fileName){
//
//        Query query = new Query();
//        query.setQuery(fileName);
//        query.setUserId("oX5hwwLxeGMcvKcu-Qk9YMsDfpQQ");
//        String data = new Gson().toJson(query);
//        String result = HttpUtil.okhttp(PropertiesUtil.getProperty("query.urldev"),data);
//        JsonParser parser = new JsonParser();//创建json解析器
//        //得到得到解析的值
//        JsonObject jsonObject = (JsonObject) parser.parse(result);
//        JsonObject jsonObject1 = (JsonObject) jsonObject.get("info");
//        result = jsonObject1.get("ask").toString();
//        return  result;
//    }
}
