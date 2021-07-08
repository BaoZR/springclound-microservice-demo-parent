//package cn.demo.storage.fallback;
//
//import cn.demo.common.model.base.BaseCode;
//import cn.demo.common.model.pojo.RequestObject;
//import cn.demo.common.model.pojo.ResponseResult;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
//import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @version 1.0
// * @Author 义云
// * @Description 此类定义@SentinelResource的属性 blockHandler 和fallbackHandler
// * @Date 2020/4/22 11:19
// */
//@Slf4j
//public class ExceptionUtil {
//
//    /**
//     * 对应@SentinelResource的属性 blockHandler
//     * @param requestObject
//     * @param e
//     * @return
//     */
//    public static ResponseResult blockExceptionHandler(RequestObject requestObject, BlockException e) {
//        log.info("Oops blockExceptionHandler:  " + e.getClass().getCanonicalName());
//        // 不同的异常返回不同的提示语
//        if (e instanceof FlowException) {
//            log.info("接口限流了");
//            return ResponseResult.fail("接口限流");
//        } else if (e instanceof DegradeException) {
//            log.info("服务降级了");
//            return ResponseResult.fail("服务熔断降级了");
//        } else if (e instanceof ParamFlowException) {
//            log.info("热点参数限流了");
//            return ResponseResult.fail("热点参数限流了");
//        } else if (e instanceof SystemBlockException) {
//            log.info("触发系统保护规则");
//            return ResponseResult.fail("触发系统保护规则");
//        } else if (e instanceof AuthorityException) {
//            log.info("触发系统保护规则");
//            return ResponseResult.fail("触发系统保护规则");
//        }
//        return ResponseResult.fail(BaseCode.CALL_TIME);
//    }
//
//    /**
//     * 对应@SentinelResource的属性 fallbackHandler
//     * @param requestObject
//     * @param e
//     * @return
//     */
//    public static ResponseResult fallbackHandler(RequestObject requestObject, Throwable e) {
//        log.info("Oops fallbackHandler: " + e.getClass().getCanonicalName());
//        // 不同的异常返回不同的提示语
//        if (e instanceof FlowException) {
//            log.info("接口限流了");
//            return ResponseResult.fail("接口限流");
//        } else if (e instanceof DegradeException) {
//            log.info("服务降级了");
//            return ResponseResult.fail("服务熔断降级了");
//        } else if (e instanceof ParamFlowException) {
//            log.info("热点参数限流了");
//            return ResponseResult.fail("热点参数限流了");
//        } else if (e instanceof SystemBlockException) {
//            log.info("触发系统保护规则");
//            return ResponseResult.fail("触发系统保护规则");
//        } else if (e instanceof AuthorityException) {
//            log.info("触发系统保护规则");
//            return ResponseResult.fail("触发系统保护规则");
//        }
//        return ResponseResult.fail(BaseCode.CALL_TIME);
//    }
//}
