package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //TestBean 을 스프링 빈으로 등록함.

    }

    static class TestBean {

        /** `Member` 는 스프링 빈이 아님.
         *  org.springframework.beans.factory.UnsatisfiedDependencyException
         *  nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException
         *  : No qualifying bean of type 'hello.core.member.Member' available
         **/
        // @Autowired //default : true
        // public void setNoBean0(Member member) {
        //     System.out.println("member = " + member);
        // }

        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }

    }

}
