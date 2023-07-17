package pr.iceworld.fernando.spring6.aop.aspect;

import org.springframework.stereotype.Service;

@Service
public class Monkey {
      
    public void stealPeaches(String name){  
        System.out.println("【猴子】"+name+"正在偷桃...");  
    }
}