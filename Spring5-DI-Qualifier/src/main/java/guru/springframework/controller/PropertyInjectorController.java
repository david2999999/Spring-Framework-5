package guru.springframework.controller;

import guru.springframework.services.GreetingService;
import guru.springframework.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

// This is a class to show what not to do
// don't use property based dependency injection
@Controller
public class PropertyInjectorController {

    // Although there are 3 Greeting service implementation
    // this variable will choose the greetingServiceImpl due to reflection
    @Autowired
    @Qualifier("greetingServiceImpl")
    public GreetingService greetingServiceImpl;

    public String sayHello(){
        return greetingServiceImpl.sayGreeting();
    }
}
