package pr.iceworld.fernando.spring6.aop.aspect;

import org.springframework.stereotype.Service;

@Service
public class AspectServiceImpl implements AspectService {

    // Around start
    // Before
    // AfterReturning / AfterThrowing
    // afterReturn
    // After
    // Around end
    @Override
    public int doAction() {
        System.out.println("运行的编码");
        return 0;
    }
}
