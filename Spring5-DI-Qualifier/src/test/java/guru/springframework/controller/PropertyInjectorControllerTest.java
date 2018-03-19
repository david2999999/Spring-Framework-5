package guru.springframework.controller;

import guru.springframework.services.GreetingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zheng on 3/18/2018.
 */
public class PropertyInjectorControllerTest {

    private PropertyInjectorController propertyInjectorController;

    @Before
    public void setUp() throws Exception {
        this.propertyInjectorController = new PropertyInjectorController();
        this.propertyInjectorController.greetingServiceImpl = new GreetingServiceImpl();
    }

    @Test
    public void testGreeting() throws Exception {
        assertEquals(GreetingServiceImpl.HELLO_GURUS, propertyInjectorController.sayHello());
    }

}