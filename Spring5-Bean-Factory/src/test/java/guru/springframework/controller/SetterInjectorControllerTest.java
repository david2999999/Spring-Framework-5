package guru.springframework.controller;

import guru.springframework.services.GreetingService;
import guru.springframework.services.GreetingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SetterInjectorControllerTest {

    private SetterInjectorController setterInjectorController;

    @Before
    public void setUp() throws Exception {
        this.setterInjectorController = new SetterInjectorController();
        this.setterInjectorController.setGreetingService(new GreetingServiceImpl());
    }

    @Test
    public void testGreeting() throws Exception {
        assertEquals(GreetingServiceImpl.HELLO_GURUS, setterInjectorController.sayHello());
    }


}