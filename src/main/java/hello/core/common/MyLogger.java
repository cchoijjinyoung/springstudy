package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// proxyMode를 추가.
// 스프링 컨테이너는 CGLIB라는 바이트코드를 조작하는 라이브러리를 사용해서
// MyLogger를 상속받은 가짜 프록시 객체를 생성한다.
// MyLogger$$EnhanceBySpringCGLIB 라는 클래스로 만들어진 객체가 대신 등록.
// ac.getBean("myLogger", MyLogger.class) 로 조회해도 프록시 객체가 조회되는 것을 확인가능.
// 그래서 의존관계 주입도 가짜 프록시 객체가 주입된다.
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create :" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close :" + this);
    }

}
