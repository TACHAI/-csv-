package com.xmduruo.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xmduruo.dto.Objectjson;

import java.util.*;

/**
 * Created by @Author tachai
 * date 2018/7/30 10:04
 *
 * @Email 1206966083@qq.com
 */
public class DemoJson {
    public static void main(String[] args) {

    }

    public static Map<String, Object> parseTestJson(String name) {

        String res = FileReadToString.toString("酒类办理流程");
//        String aa =testQuery("我要卖酒");
        JsonParser parser = new JsonParser();//创建json解析器

        JsonObject jsonObject = (JsonObject) parser.parse(res);


        JsonObject json1 = jsonObject.getAsJsonObject("data");

        /**
         * 将开始数据转换成string数组
         * 将不理解数据转换成string数组
         * 将结束数据转换成string数组
         */
        String[] startUp = StringUtils.trim(StringUtils.trim(json1.get("startUp").toString(), '['), ']').split(",");
        String[] understand_default = StringUtils.trim(StringUtils.trim(json1.get("understand_default").toString(), ']'), '[').split(",");
        String[] finished_default = StringUtils.trim(StringUtils.trim(json1.get("finished_default").toString(), '['), ']').split(",");

        for (int i = 0; i < startUp.length; i++) {
            startUp[i] = StringUtils.trim(startUp[i], '"');
            System.out.println(startUp[i]);
        }
        for (int i = 0; i < understand_default.length; i++) {
            understand_default[i] = StringUtils.trim(understand_default[i], '"');
            System.out.println(understand_default[i]);
        }
        for (int i = 0; i < finished_default.length; i++) {
            finished_default[i] = StringUtils.trim(finished_default[i], '"');
            System.out.println(finished_default[i]);
        }

        JsonObject json3 = json1.getAsJsonObject("tasks");


        List<String> list = new LinkedList<>();
        String match = "";
        List<String> list1 = diguiJson(list, json3, match);

        Map<String, Object> map = new Hashtable<>();
        map.put("startUp", startUp);
        map.put("understand_default", understand_default);
        map.put("finished_default", finished_default);
        map.put("list", list1);

//        JsonObject json2 = json1.getAsJsonObject("taskInfo");
//        //实时查询中不会有taskInfo
//        if (null == json2) {
//            JsonObject json3 = json1.getAsJsonObject("tasks");
//            return json3;
//        } else {
//            JsonObject json3 = json2.getAsJsonObject("tasks");
//            return json3;
//        }
        return map;
    }

    /**
     * 递归得到所有的json分支
     *
     * @param jsonObject
     * @return
     */
    public static List<String> diguiJson(List<String> listStr, JsonObject jsonObject, String matchStr) {

        JsonArray jsonArray = jsonObject.getAsJsonArray("answer");
//        "type": "multi",

        if (jsonArray != null) {
            int len = jsonArray.size();

            boolean temp = false;
            String mm="";
            if (jsonObject.get("type") != null && jsonObject.get("type").toString().indexOf("multi") > -1) {
//                temp = true;
//               String[] matchStrS = matchStr.split("-");
//               for (int i= 0;i<matchStrS.length-2;i++){
//                   mm += "-"+matchStrS[i];
//               }
//                System.out.println("-----"+matchStr);
            }

            for (int i = 0; i < len; i++) {
                JsonObject json = (JsonObject) jsonArray.get(i);
                //记录match里面的值
                JsonArray jsonObject1 = json.getAsJsonArray("match");
                String id = json.get("id").toString();
//                if (jsonObject.get("type") != null && jsonObject.get("type").toString().indexOf("multi") > -1) {
//                    if(jsonObject.get("match")!=null){
//                        matchStr += "-" + id + jsonObject.get("match").toString();
//                    }
//                }else if (jsonObject1 != null) {
//                    matchStr += "-" + id + jsonObject1.toString();
//                }
                if (jsonObject1 != null) {
                    matchStr += "-" + id + jsonObject1.toString();
                }

                    if (json.get("isLeaf") !=null) {
                        if (jsonObject.get("type") != null && jsonObject.get("type").toString().indexOf("multi") > -1) {
                            if(i==len-1){
                                listStr.add(matchStr);
                            }
                        }else {
                            listStr.add(matchStr);

                        }
                    }
                DemoJson.diguiJson(listStr, json, matchStr);
                if (jsonObject.get("type") != null && jsonObject.get("type").toString().indexOf("multi") > -1) {


                }else if(jsonObject.get("type") != null && jsonObject.get("type").toString().indexOf("single") > -1){

                }
            }

        }

        return listStr;

    }


    //酒类文件
    /**
     * 遍历测试用例
     * @param jsonObject
     * @param str
     * @return
     */
    public static List<String[]> matchAddSuffix(JsonObject jsonObject,String[] str){
        List<String[]> list = new ArrayList<>();
        for (int i=0;i<str.length;i++){
            String[] temp = WeChatParseJson.getMatchById(jsonObject,Integer.parseInt(str[1]));
            list.add(temp);
        }
        return list;
    }

}
