package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component Annotation 붙은 모든 클래스를 스프링 빈으로 등록한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //AppConfig 가 컴포넌트 스캔되는 것을 방지하기 위한 필터
)
public class AutoAppConfig {
    //@Bean 클래스가 없다.
}
