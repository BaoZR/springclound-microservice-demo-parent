//package cn.demo.common.annotation.aspect;
//
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import cn.demo.common.annotation.NoRepeatSubmit;
//import cn.demo.common.util.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//@Aspect
//@Component
//public class NoRepeatSubmitAspect {
//
//    /**
//     * 前置
//     */
//    @Before("@annotation(noRepeatSubmit)")
//    public void before(JoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) throws InterruptedException {
//        if (Objects.nonNull(noRepeatSubmit)) {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            String servletPath = Objects.requireNonNull(attributes).getRequest().getServletPath();
//            String key = JwtUtil.getMemberId() + "-" + servletPath;
//            // 如果缓存中有这个url视为重复提交
//            boolean lock = RedisLockUtil.tryLock(key, 0, 10, TimeUnit.SECONDS);
//            if (!lock) {
//                log.error("重复提交");
//                throw new BaseException(BaseCode.REPEAT_REQUEST);
//            }
//        }
//    }
//
//    /**
//     * 后置
//     *
//     * @param joinPoint
//     * @param noRepeatSubmit
//     */
//    @AfterReturning("@annotation(noRepeatSubmit)")
//    public void doAfterReturning(JoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) {
//        if (Objects.nonNull(noRepeatSubmit)) {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            String servletPath = Objects.requireNonNull(attributes).getRequest().getServletPath();
//            String key = JwtUtil.getMemberId() + "-" + servletPath;
//            RedisLockUtil.unlock(key);
//        }
//    }
//
//
//}
