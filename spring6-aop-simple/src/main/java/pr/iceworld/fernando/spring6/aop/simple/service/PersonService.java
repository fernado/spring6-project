package pr.iceworld.fernando.spring6.aop.simple.service;

import pr.iceworld.fernando.spring6.aop.simple.dto.Person;

public interface PersonService {

    void createPerson(String name);

    Person getPerson(Long id);
}
