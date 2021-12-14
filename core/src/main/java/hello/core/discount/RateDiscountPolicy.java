package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy //커스텀 애노테이션 사용으로 컴파일타임에 오류를 발견할 수 있다.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; //돈 관련된 부분은 로직 테스트를 정말 많이해보고, 신중해야함
        } else {
            return 0;
        }
    }
}
