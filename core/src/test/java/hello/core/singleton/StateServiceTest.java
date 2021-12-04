package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StateServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        int userAPrice = statefulService1.getPrice();
        System.out.println("userAPrice = " + userAPrice);
        //ThreadB : 사용자B 주문 금액 조회
        int userBPrice = statefulService2.getPrice();
        System.out.println("userBPrice = " + userBPrice);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(statefulService2.getPrice()).isEqualTo(20000);
    }

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA : A사용자가 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        //ThreadB : B사용자가 20000원 주문
        int userBPrice = statelessService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        System.out.println("userAPrice = " + userAPrice);
        //ThreadB : 사용자B 주문 금액 조회
        System.out.println("userBPrice = " + userBPrice);

        assertThat(userAPrice).isEqualTo(10000);
        assertThat(userBPrice).isEqualTo(20000);
    }

}