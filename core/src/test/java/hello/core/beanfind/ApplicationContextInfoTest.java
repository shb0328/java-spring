package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() { //junit5 부터는 public 명시하지 않아도 됨
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //for문 자동완성 : iter + tab
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findAllApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //BeanDefinition : 각 Bean에 대한 메타데이터
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //BeanDefinition.ROLE_APPLICATION : 개발자가 직접 등록한 Bean, 외부라이브러리에서 등록한 Bean
            //BeanDefinition.ROLE_INFRASTRUCTURE : 스프링 내부에서 등록한 Bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("인프라 빈 출력하기")
    void findAllInfraBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //BeanDefinition : 각 Bean에 대한 메타데이터
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //BeanDefinition.ROLE_APPLICATION : 개발자가 직접 등록한 Bean, 외부라이브러리에서 등록한 Bean
            //BeanDefinition.ROLE_INFRASTRUCTURE : 스프링 내부에서 등록한 Bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }
}
