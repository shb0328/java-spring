package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //1.생성자 주입
    // 불변/필수 의존관계일 때.
    // 주로 field 에 final 선언해서 컴파일 타임에 오류를 체크할 수 있도록 한다.
    private final MemberRepository memberRepository; //final field 는 선언문에서 즉시 할당하든, 생성자를 통해서 객체 생성 즉시 할당해야함.
    private final DiscountPolicy discountPolicy;

    @Autowired //생성자가 딱 1개일 때 생략가능 (스프링 빈에 대해서만)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {//주로, 문서에 null을 허용하지 않는 이상, 생성자에 요구되는 파라미터에는 null을 넘기면 안된다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //2.수정자 주입
    //  불변/필수 의존관계가 아닐 때.
    //  생성자 주입을 사용하지 않았을 때, field 에 final 제거하고 사용 가능
    /*
    private MemberRepository memberRepository; //final field 는 선언문에서 즉시 할당하든, 생성자를 통해서 객체 생성 즉시 할당해야함.
    private DiscountPolicy discountPolicy;

    @Autowired(required = false) // 선택적 주입. 만약 MemberRepository 스프링 빈이 없어도 괜찮다.
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

    //3.필드 주입
    // Field Injection is not recommended
    // 테스트 시에 구현체를 외부에서 임의로 주입 및 변경할 방법이 없다.
    /*
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
    */

    //4.일반 메서드 주입
    // 수정자 주입과 비슷하게 동작하지만,
    // 한번에 여러 필드를 주입 받을 수 있다는 장점이 있다.
    // 일반적으로 잘 사용하지 않는다.
    /*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //ISP 원칙을 잘 지킴. Order는 Discount 사정이 바뀌어도 알빠아님.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
