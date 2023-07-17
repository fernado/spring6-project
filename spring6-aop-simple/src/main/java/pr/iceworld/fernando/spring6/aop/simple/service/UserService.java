package pr.iceworld.fernando.spring6.aop.simple.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import pr.iceworld.fernando.spring6.aop.simple.component.UserComponent;
import pr.iceworld.fernando.spring6.aop.simple.dto.User;

import java.util.List;

@Service
public class UserService {

    @Resource
    UserComponent userComponent;

    public User get(Long id) {
        return userComponent.get(id);
    }

    public User add(User user) {
        return userComponent.add(user);
    }

    public List<User> list() {
        return userComponent.list();
    }
}
