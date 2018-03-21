package guru.springframework;

import guru.springframework.examplebeans.FakeDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PropertySourceApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PropertySourceApp.class, args);

		FakeDataSource fakeDataSource = (FakeDataSource) ctx.getBean(FakeDataSource.class);

		System.out.println(fakeDataSource);


	}
}
