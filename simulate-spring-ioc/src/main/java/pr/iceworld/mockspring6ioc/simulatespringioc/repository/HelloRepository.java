package pr.iceworld.mockspring6ioc.simulatespringioc.repository;

import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.CustomRepository;

@CustomRepository
public class HelloRepository {

    public void sayHello(String name) {
        System.out.println("Hello - " + name);
    }
}