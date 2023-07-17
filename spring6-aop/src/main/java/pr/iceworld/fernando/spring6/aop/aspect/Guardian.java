package pr.iceworld.fernando.spring6.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class Guardian {  
      
    @Pointcut("execution(* pr.iceworld.fernando.spring6.aop.aspect.Monkey.stealPeaches(..))")
    public void foundMonkey(){}  
  
    @Before(value="foundMonkey()")
    // @Before 获取不到参数
    //@Before(value="foundMonkey() && args(name,..)")
    public void foundBefore(){
        System.out.println("【守护者】发现猴子正在进入果园...");  
    }  

    @Around(value="foundMonkey()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //proceedingJoinPoint.getArgs() = [Ljava.lang.Object;@3e6104fc
        //Dev
        //params : [a = 5, b = 8]
        Object[] args = proceedingJoinPoint.getArgs();
        List<Object> ls = Arrays.asList(args);
        ls.forEach(System.out::println);
        System.out.println("2---------------------------");
        return proceedingJoinPoint.proceed();
    }

    // After 可以获取到参数
    @AfterReturning("foundMonkey() && args(name,..)")
    public void foundAfter(String name){  
        System.out.println("【守护者】抓住了猴子,守护者审问出了猴子的名字叫“"+name+"”...");  
    }


} 