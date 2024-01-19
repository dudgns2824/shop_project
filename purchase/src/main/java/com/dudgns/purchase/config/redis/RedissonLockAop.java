package com.dudgns.purchase.config.redis;

import com.dudgns.purchase.annotation.RedissonLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnExpression("${ableRedissonLock:true}")
public class RedissonLockAop {
    private final RedissonClient redissonClient;

    @Around("@annotation(com.dudgns.purchase.annotation.RedissonLock) && args(name, uuid)")
    public Object lock(final ProceedingJoinPoint joinPoint, String name, String uuid) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);

        RLock rLock = redissonClient.getLock(name+"|"+uuid);

        log.info("RedissonLock 적용");

        long waitTime = redissonLock.waitTime();
        long leaseTime = redissonLock.leaseTime();
        TimeUnit timeUnit = redissonLock.timeUnit();
        try {
            boolean available = rLock.tryLock(waitTime, leaseTime, timeUnit);
            if (!available) {
                throw new Exception();
            }
            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                log.info("RedissonLock 언락");
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                throw e;
            }
        }
    }

}
