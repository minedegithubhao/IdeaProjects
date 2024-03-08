package com.smartdrm.common;

import java.io.Serializable;

/**
 * @author cxdpc
 * @data 2023-12-25 10:48
 * @Description  响应实体
 */
public class AjaxResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**响应吗*/
    private int code;

    /**相应数据（DataGrid组件使用）*/
    private Object rows;

    /**数据条数（DataGrid组件使用）*/
    private int total;

    /**是否成功（DataGrid组件使用）*/
    private boolean success;

    /**相应信息（DataGrid组件使用）*/
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static AjaxResult success(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(true);
        ajaxResult.setCode(ResultType.SUCCESS.value());
        ajaxResult.setMessage("操作成功");
        return ajaxResult;
    }

    public static AjaxResult success(String message){
        AjaxResult success = success();
        success.setMessage(message);
        return success;
    }

    public static AjaxResult success(String message, Object data){
        AjaxResult success = success();
        success.setRows(data);
        success.setMessage(message);
        return success;
    }

    public static AjaxResult success(Object data, int total){
        AjaxResult success = success();
        success.setRows(data);
        success.setTotal(total);
        return success;
    }

    public static AjaxResult error(String message){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(false);
        ajaxResult.setCode(ResultType.FAILURE.value());
        ajaxResult.setMessage(message);
        return ajaxResult;
    }
}
