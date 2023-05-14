package com.qrcode.until;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int status;
    private String msg;
    private T data;

    public ResponseResult(){}

    public ResponseResult(int status, String msg){
        this.status = status;
        this.msg = msg;
    }
    public ResponseResult(T data, String msg, int status){
        this(status,msg);
        this.data = data;
        this.msg = msg;
    }

    public static ResponseResult ok(){
        ResponseResult result = new ResponseResult();
        result.setStatus(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMessage());
        return result;
    }

    //根据指定枚举类型值生成一个ResponseResult的结果
    public static ResponseResult error(ResultCode resultCode){
        ResponseResult result = new ResponseResult();
        result.setStatus(resultCode.getCode());
        result.setMsg(resultCode.getMessage());
        return result;
    }

    public static ResponseResult<Void> SUCCESS = new ResponseResult<>(200,"成功");
    public static ResponseResult<Void> INTEVER_ERROR = new ResponseResult<>(500,"服务器错误");
    public static ResponseResult<Void> NOT_FOUND = new ResponseResult<>(404,"未找到");

}
