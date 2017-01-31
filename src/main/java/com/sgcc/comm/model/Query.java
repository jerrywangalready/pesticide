package com.sgcc.comm.model;

import java.util.List;

/**
 * @author jerrywang
 * @create 2017/1/30.
 */
public class Query {

    private int pageNum;

    private int pageSize;

    private long total;

    private Object list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
