package com.kaishengit.weixin.exception;

public class WeiXinException extends RuntimeException {
    public WeiXinException(){

    }
    public WeiXinException(String message){
        super(message);
    }
}
