package com.smartdrm.entity.common;

/**
 * @author cxdpc
 * @date 2023/12/26 09:18
 * @description datagrid分页字段
 */
public class CommonParam {

    private Integer page;

    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
