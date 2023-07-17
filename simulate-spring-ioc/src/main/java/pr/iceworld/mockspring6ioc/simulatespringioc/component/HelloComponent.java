package pr.iceworld.mockspring6ioc.simulatespringioc.component;

import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.CustomAutowired;
import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.CustomComponent;
import pr.iceworld.mockspring6ioc.simulatespringioc.repository.HelloRepository;

@CustomComponent
public class HelloComponent {

    @CustomAutowired
    HelloRepository helloRepository;

    public void sayHello(String name) {
        helloRepository.sayHello(name);
    }
}
