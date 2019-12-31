package com.kenny.logistics.model.vo;


import lombok.Data;

import java.util.List;

/**
 * Created by WKL on 2017-3-3.
 */
@Data
public class PageVo<T> {
    //("总数")
    private Long total;
    //("第几页")
    private Long current;
    //("每页数量")
    private Long pageSize;
    //("内容列表")
    private List<T> item;
}
