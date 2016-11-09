package com.lzx2005.dto;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28 0028.
 */
public class PageResult<T> {
    private int curPage;//当前页码
    private int pageSize;//每页多少行
    private int totalPage;//共多少页
    private long totalRow;//共多少数据
    private ArrayList<T> list;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
