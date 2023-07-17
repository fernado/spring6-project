package pr.iceworld.fernando.spring6.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class LogAspect {
//    @Before("bean(helloService)")
//    @Before("within(pr.*)")  zhl包下的子类
//    @Before("within(pr..*)") zhl包下的所有子孙类
//    @Before("execution(public void pr..*.*(..))")  public 可以省略


//    @Pointcut("execution(* pr..*.*())")

    /**
     * execution([ 权限修饰符 ] [ 返回类型 ] [ 类全路径 ] [ 方法名称 ]([ 参数列表 ]) )
     * 第一个* 代表 任意修饰符及任意返回类型
     * 第二个* 代表 当前类中的任意方法
     * .. 代表任意参数列表
     */
    @Pointcut("execution(public int pr.iceworld.fernando..*(..))")
    public void pointcut() {
        System.out.println("init...........");
    }

    @Before("pointcut()")
    public void before(JoinPoint jointPoint) {
        Object[] args = jointPoint.getArgs();
        String methodName = jointPoint.getSignature().getName();
        Class<?> targetClass = jointPoint.getTarget().getClass();
        System.out.println("[普通前置日志before]：方法名称 " + methodName + " 目标对象的类型 " +
                targetClass + " 参数 " + Arrays.toString(args));
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[普通返回日志afterReturn]:方法调用完成 方法名：" + methodName + " 返回值信息：" + result);

    }

    @AfterThrowing(value = "pointcut()", throwing = "throwable")
    public void throwable(JoinPoint jointPoint, Throwable throwable) {
        String methodName = jointPoint.getSignature().getName();
        System.out.println("[普通异常日志throwable] 方法调用异常 方法名：" + methodName + "异常信息：" + throwable);
    }

    @After("pointcut()")
    public void after() {
        System.out.println("普通后置通知after");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            System.out.println("环绕前置通知around");//类似于前置通知
            result = proceedingJoinPoint.proceed();
            System.out.println("环绕返回通知around");//类似于返回通知
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕异常通知around" + throwable.getMessage());//类似于异常通知
        } finally {
            System.out.println("环绕后置通知around");//类似于后置通知
        }
        return result;
    }
}
