package guru.springframework.controller;

import guru.springframework.services.GreetingServiceImpl;

// This is a class to show what not to do
// don't use property based dependency injection
public class PropertyInjectorController {

    public GreetingServiceImpl greetingService;

    String sayHello(){
        return greetingService.sayGreeting();
    }
}
