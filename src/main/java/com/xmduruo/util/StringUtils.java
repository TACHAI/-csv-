package com.xmduruo.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by @Author tachai
 * date 2018/7/17 11:03
 *
 * @Email 1206966083@qq.com
 */
@Slf4j
public class StringUtils {

    /**
     * 去除前后指定字符
     * @param args   目标字符串
     * @param beTrim 要删除的指定字符
     * @return       删除之后的字符串
     */
    public static String trim(String args,char beTrim){
        int st = 0;
        int len = args.length();
        char[] val = args.toCharArray();
        char sbeTrim = beTrim;

        while (val[st]<=sbeTrim){
            st++;
        }
        while (val[len-1]<=sbeTrim){
            len--;
        }
        return ((st>0)||(len<args.length()))?args.substring(st,len):args;
    }

    public static void main(String[] args) {
        String mm = new String("65\"4\"54\"");
        String end = trim(mm,'"');
        System.out.println(end+"得到的值");
    }

}
