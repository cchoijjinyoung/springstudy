package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
//      스프링으로 전환해보자.

        // ApplicationContext : 스프링 컨테이너
        // 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 설정 정보로 사용한다.
        // 여기서 @Bean이라 적힌 매서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
        // 이렇게 등록된 객체를 '스프링 빈'이라 한다.
        // 스프링 빈은 @Bean이 붙은 매서드의 명을 스프링 빈의 이름으로 사용한다.
        // ex) memberService, orderService
        // @Bean(name = "mmm") 으로 스프링 빈의 이름을 변경해줄 수 있다.
        // 그러나 관례상 그냥 냅두자.
        //
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        // 스프링컨테이너에서 "orderService라는애 받을건데, 타입은 OrderService.class야." 라고 말해주는 것.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // order를 출력하면 toString이 출력이됨.
        System.out.println("order =" + order);
    }
}
