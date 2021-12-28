package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFine() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCnt();
        assertThat(prototypeBean1.getCnt()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCnt();
        assertThat(prototypeBean2.getCnt()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int cnt1 = clientBean1.logic();
        assertThat(cnt1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int cnt2 = clientBean2.logic();
        assertThat(cnt2).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean_usdObjectProvider.class, PrototypeBean.class);

        ClientBean_usdObjectProvider clientBean1 = ac.getBean(ClientBean_usdObjectProvider.class);
        int cnt1 = clientBean1.logic();
        assertThat(cnt1).isEqualTo(1);

        ClientBean_usdObjectProvider clientBean2 = ac.getBean(ClientBean_usdObjectProvider.class);
        int cnt2 = clientBean2.logic();
        assertThat(cnt2).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean_useProvider.class, PrototypeBean.class);

        ClientBean_useProvider clientBean1 = ac.getBean(ClientBean_useProvider.class);
        int cnt1 = clientBean1.logic();
        assertThat(cnt1).isEqualTo(1);

        ClientBean_useProvider clientBean2 = ac.getBean(ClientBean_useProvider.class);
        int cnt2 = clientBean2.logic();
        assertThat(cnt2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
//        private final PrototypeBean prototypeBean;
        @Autowired
        ApplicationContext ac;

        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCnt();
            return prototypeBean.getCnt();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean_usdObjectProvider {

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCnt();
            return prototypeBean.getCnt();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean_useProvider {

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCnt();
            return prototypeBean.getCnt();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int cnt;

        public void addCnt() {
            cnt++;
        }

        public int getCnt() {
            return cnt;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
