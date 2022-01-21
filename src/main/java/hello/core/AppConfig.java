package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 구성을 담당.
@Configuration
public class AppConfig {
    // 중복제거 필요, 역할에 따른 구현이 잘 안보임.
    /*public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }*/
    // new MemoryMemberRepository() 를 하나로 빼주어서
    // memberService, orderService, memberRepository 로 딱딱 나누어 줄 예정.


    // AppConfig가 어떻게 구성되어있는지 한 눈에 파악가능
    // 이제 MemberRepository가 변경되면 한 부분만 변경해주면 된다.
    // 마찬가지로 할인정책도 바뀌게되면 DiscountPolicy만 변경하면 된다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
