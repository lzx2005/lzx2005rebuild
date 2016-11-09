package com.lzx2005.tool;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class StrTool {
    public static boolean isNull(String str){
        return str==null || str.equalsIgnoreCase("");
    }

    public static boolean isNotNull(String str){
        return str!=null && !str.equalsIgnoreCase("");
    }

    public static boolean allIsNotNull(String ...strs){
        for(String s : strs){
            if(isNull(s)){
                return false;
            }
        }
        return true;
    }
}
