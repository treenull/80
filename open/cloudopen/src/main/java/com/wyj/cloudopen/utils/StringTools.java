package com.wyj.cloudopen.utils;

import java.util.List;

/**
 * @author: wu.yy
 * @date: 2017/10/24 10:16
 */
public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
    
    public static String listToString(List<Integer> list){
 	   if(list==null){
 	      return null;
 	   }
 	   StringBuilder result = new StringBuilder();
 	   boolean first = true;
 	   //第一个前面不拼接","
 	   for(int string :list) {
 	      if(first) {
 	         first=false;
 	      }else{
 	         result.append(",");
 	      }
 	      result.append(string);
 	   }
 	   return result.toString();
 	}
}
