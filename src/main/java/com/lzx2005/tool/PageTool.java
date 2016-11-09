package com.lzx2005.tool;

import com.lzx2005.dto.PageResult;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class PageTool {
    public int getPage(long count,int pageSize){
        int totalPage = (int) (count/pageSize);
        int mod = (int) (count%pageSize);
        System.out.println(totalPage+","+mod);
        if(mod>0){
            totalPage++;
        }
        return totalPage;
    }
}
