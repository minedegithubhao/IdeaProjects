package com.smartdrm.common;

/**
 * @author cxdpc
 * @date 2023-12-25 11:23
 */
public enum ResultType {


    /**请求成功*/
    SUCCESS(200),
    /**请求失败，系统内部错误*/
    FAILURE(500);

    private final int value;

    ResultType(int value) {
        this.value = value;
    }

    public int value(){
        return this.value;
    }

}
