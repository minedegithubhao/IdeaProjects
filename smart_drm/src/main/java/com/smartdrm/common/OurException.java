package com.smartdrm.common;

/**
 * @author cxdpc
 * @date 2023/12/28 14:26
 * @description 自定义异常类
 */
public class OurException extends RuntimeException{

    public OurException(){
        super();
    }

    public OurException(String message){
        super(message);
    }
}
