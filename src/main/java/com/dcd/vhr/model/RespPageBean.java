package com.dcd.vhr.model;

import java.util.List;

/**
 * Description: 用于保存分页查询的结果
 * @Date 2022/7/8 0:03
 * @Param
 */
public class RespPageBean {
    //一共查了多少条记录
    private Long total;
    //数据
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
