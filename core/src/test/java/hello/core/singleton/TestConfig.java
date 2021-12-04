package hello.core.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public StatefulService statefulService() {
        return new StatefulService();
    }

    @Bean
    StatelessService statelessService() {
        return new StatelessService();
    }
}
