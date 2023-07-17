package pr.iceworld.fernando.spring6.aop.simple.service.impl;

import org.springframework.stereotype.Service;
import pr.iceworld.fernando.spring6.aop.simple.dto.Person;
import pr.iceworld.fernando.spring6.aop.simple.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {


    @Override
    public void createPerson(String name) {

    }

    @Override
    public Person getPerson(Long id) {
        return null;
    }
}
