package pr.iceworld.mockspring6ioc.simulatespringioc.service;

import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.CustomAutowired;
import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.CustomService;
import pr.iceworld.mockspring6ioc.simulatespringioc.component.HelloComponent;

@CustomService
public class HelloService {

    @CustomAutowired
    HelloComponent helloComponent;

    public void sayHello(String name) {
        helloComponent.sayHello(name);
    }
}