package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid; //HTTP 요청 당 생성되는 각각의 빈을 구분하기 위해서 사용 
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String msg) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + msg);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 유일한 UUID 발급
        System.out.println("[" + uuid + "] request scope bean create : " + this);

    }

    //고객 요청이 서버에서 빠져나갈 때 스프링 컨테이너에 의해 close 호출되며 스코프 종료
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
