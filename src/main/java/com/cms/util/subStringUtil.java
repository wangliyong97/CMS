package com.cms.util;

/**
 * Created by wangliyong on 2019/1/25.
 */
public class subStringUtil {
    /**
     * 返回富文本中的第一个图片路径作为活动封面
     * @param content
     * @return
     */
    public static String subImages(String content) {

        String str = content;
        String strStart = "src=";
        //String strEnd = "\">";
		/* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = content.length();
        String s="";
        String[] result=new String[]{"",""};
        if(strStartIndex>0){
            s = str.substring(strStartIndex, strEndIndex).substring(
                    strStart.length());
            result = s.split("\"");
        }
        return result[1];
    }

    /**
     * 返回分割后的关键字
     * @param keyword
     * @return
     */
    public static String subKeyword(String keyword) {
        String str = keyword.replace("；", ";");
        return str;
    }
}
