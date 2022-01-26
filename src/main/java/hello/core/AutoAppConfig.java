package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackage = "hello.core.member",  //
        // package 중 member만 componentScan 한다.
        // 탐색할 패키지의 시작위치 지정. 이 패키지포함 하위패키지 모두 탐색.
        // 권장하는 방법 - 패키지위치 지정을 따로 안하고 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다.
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //Configuration 은 뺄거야
public class AutoAppConfig {

}
