package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//@Qualifier 와 같은 메타애노테이션 설정
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// + 한 가지 더
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
