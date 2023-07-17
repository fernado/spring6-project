package pr.iceworld.fernando.spring6.aop.annotation;

import org.springframework.stereotype.Service;

@Test("Dev")
@Service
public class CheckAnnotationTest {

    @TestMethod
    public void doAction(String a, String b) {
        System.out.println("params : [a = " + a + ", b = " + b + "]");
    }

}
