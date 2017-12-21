package com.kenny.logistics.driver.model;

import java.util.List;

/**
 * Created by WKL on 2017-3-3.
 */
public class PageResponse<T> {
    //"总数")
    private Integer total;
    //"从第几个开始")
    private Integer offset;
    //"每页数量")
    private Integer pageSize;
    //"内容列表")
    private List<T> item;

    public PageResponse(){}

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
