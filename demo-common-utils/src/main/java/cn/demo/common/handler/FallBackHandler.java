package cn.demo.common.handler;

import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.pojo.ResponseResult;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/21 13:43
 */
public class FallBackHandler {
    public static ResponseResult CallTimeNoArg(Throwable throwable){
        return new ResponseResult(BaseCode.CALL_TIME.getCode(),BaseCode.CALL_TIME.getMessage());
    }
    public static ResponseResult CallTimeOneArg(String arg1,Throwable throwable){
        return new ResponseResult(BaseCode.CALL_TIME.getCode(),BaseCode.CALL_TIME.getMessage());
    }

    public static ResponseResult CallTimeTwoArg(String arg1,String arg2,Throwable throwable){
        return new ResponseResult(BaseCode.CALL_TIME.getCode(),BaseCode.CALL_TIME.getMessage());
    }
}
