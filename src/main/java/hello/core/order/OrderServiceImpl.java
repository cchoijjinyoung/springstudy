package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// OrderServiceImpl 에서는 할인에 대해서 관여하지 않음. discountPolicy 너가 처리해. 나한테 값만 던져줘
// --> 좋은 설계 (단일 체계 원칙)
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스만 의존

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 위 코드를 할인정책을 퍼센트로 바꾸려면 new FixDiscountPolicy() 를 new Rate로 바꿔야한다.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 근데 이러한 코드는 DiscountPolicy만 의존하지 않는다.
    // FixDiscountPolicy라는 구현체 클래스에도 의존하고 있다.
    // --> SOLID중 DIP 위반
    // 그래서 FixDiscountPolicy() 를 Rate로 변경하는 순간 OrderServiceImpl의 소스 코드도 변경하였으므로
    // OCP 위반이다.
    // 인터페이스에만 의존하도록 의존관계를 변경해야한다.

    // 여기서 이 문제를 해결하려면 누군가 구현 객체를 대신 생성하고 주입해주어야한다.
    // AppConfig -- 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
    // 구현객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스를 만들자

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문생성 요청이 들어오면,
        //회원정보 조회 후 할인정책을 넘긴다.
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
