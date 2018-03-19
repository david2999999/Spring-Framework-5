package guru.springframework;

import guru.springframework.controller.ConstructorInjectedController;
import guru.springframework.controller.MyController;
import guru.springframework.controller.PropertyInjectorController;
import guru.springframework.controller.SetterInjectorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

		MyController controller = (MyController) ctx.getBean("myController");

		controller.hello();

		// the classes are scanned with the @Component
		// the fields of each class are @Autowired
		System.out.println(ctx.getBean(PropertyInjectorController.class).sayHello());
		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
		System.out.println(ctx.getBean(SetterInjectorController.class).sayHello());
	}
}
