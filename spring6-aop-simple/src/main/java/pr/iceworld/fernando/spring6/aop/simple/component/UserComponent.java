package pr.iceworld.fernando.spring6.aop.simple.component;

import org.springframework.stereotype.Component;
import pr.iceworld.fernando.spring6.aop.simple.dto.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserComponent {

    private static final Map<Long, User> users = new HashMap<>();
    private static Long id = 1L;
    public User get(Long id) {
        return users.get(id);
    }

    public User add(User user) {
        users.put(id, user);
        user.setId(id);
        id += 1;
        return user;
    }

    public List<User> list() {
        return users.values().stream().toList();
    }
}
