package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component Annotation 붙은 모든 클래스를 스프링 빈으로 등록한다.
//        basePackages = {"hello.core.discount","hello.core.member","hello.core.order"}, //@Component 탐색하는 시작위치 지정. 없으면 컴포넌트 스캔을 위해 '모든' Java 코드를 검색하느라 많은 시간이 소요됨.
//        basePackageClasses = AutoAppConfig.class, //@ComponentScan 이 선언된 파일의 package hello.core; 참고하여 hello.core 부터 탐색 (default)
        //권장 : basePackage 를 지정하지 않고, AppConfig 위치를 프로젝트 최상단에 둔다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //AppConfig 가 컴포넌트 스캔되는 것을 방지하기 위한 필터
)
public class AutoAppConfig {
    //@Bean 클래스가 없다.

    // 필드 주입을 사용해도 되는 경우 2) Configuration (not recommended)
//    @Autowired private OrderService orderService;

}
