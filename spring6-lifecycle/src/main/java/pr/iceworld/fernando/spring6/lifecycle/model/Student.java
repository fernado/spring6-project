package pr.iceworld.fernando.spring6.lifecycle.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("student")
@ToString
@Data
public class Student {

    @Value("${user.username:alvin}")
    private String username;

    @Value("${user.age:12}")
    private int age;


}