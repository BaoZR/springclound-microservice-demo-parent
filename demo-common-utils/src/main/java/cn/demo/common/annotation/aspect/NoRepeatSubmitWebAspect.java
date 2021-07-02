package cn.demo.common.annotation.aspect;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.demo.common.annotation.NoRepeatSubmitWeb;
import cn.demo.common.exception.BaseException;
import cn.demo.common.model.base.BaseCode;
import cn.demo.common.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2021/1/21 17:18
 */
@Slf4j
@Aspect
@Component
public class NoRepeatSubmitWebAspect {

    /**
     * 前置
     */
    @Before("@annotation(noRepeatSubmitWeb)")
    public void doSysLogBefore(JoinPoint joinPoint, NoRepeatSubmitWeb noRepeatSubmitWeb) throws InterruptedException {
        if (Objects.nonNull(noRepeatSubmitWeb)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
            HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
            String key = sessionId + "-" + request.getServletPath();
            // 如果缓存中有这个url视为重复提交
            boolean lock = RedisLockUtil.tryLock(key, 0, 2, TimeUnit.SECONDS);
            if (!lock) {
                log.error("重复提交");
                throw new BaseException(BaseCode.REPEAT_REQUEST);
            }
        }
    }
}
