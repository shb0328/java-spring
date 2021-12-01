package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository; //final field는 선언문에서 즉시 할당하든, 생성자를 통해서 객체 생성 즉시 할당해야함.
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //ISP 원칙을 잘 지킴. Order는 Discount 사정이 바뀌어도 알빠아님.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
