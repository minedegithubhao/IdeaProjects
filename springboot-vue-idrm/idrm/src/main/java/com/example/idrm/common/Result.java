package com.example.idrm.common;

import lombok.Data;

/**
 * @author cxdpc
 * @date 2024/3/26 10:42
 */
@Data
public class Result {

    private int code; // 响应码

    private String msg; // 提示信息

    private long total; // 总记录数

    private Object data; // 数据

    public static Result fail(){
        return result(400, "失败", 0, null);
    }

    public static Result suc(){
        return result(200, "成功", 0, null);
    }

    public static Result suc(Object data){
        return result(200, "成功", 0, data);
    }

    public static Result suc(long total, Object data){
        return result(200, "成功", total, data);
    }

    public static Result result(int code, String msg, long total, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }
}
