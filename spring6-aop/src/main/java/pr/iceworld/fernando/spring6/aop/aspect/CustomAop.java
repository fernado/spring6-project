package pr.iceworld.fernando.spring6.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomAop {

    @Pointcut("@annotation(pr.iceworld.fernando.spring6.aop.annotation.TestMethod)")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Object result = proceedingJoinPoint.proceed(args);

        log.info("请求参数：{}", JSONObject.toJSONString(args));
        log.info("返回结果：{}", JSONObject.toJSONString(result));
        return result;

    }
}
