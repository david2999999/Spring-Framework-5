package guru.springframework.controller;

import guru.springframework.services.GreetingService;

public class SetterInjectorController {

    private GreetingService greetingService;

    String sayHello(){
        return greetingService.sayGreeting();
    }

    // create a setter based dependency injection
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
