package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@ComponentScan 을 포함한다. 즉, 스프링부트를 사용하면 따로 @ComponentScan 을 사용할 필요가 없다.
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
