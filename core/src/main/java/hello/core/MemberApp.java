package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); //AppConfig에서 직접 memberService를 찾아온는게 아니라 스프링 컨테이너를 통해서 얻어와야함.

        //객체(Bean)들을 관리하는 스프링 컨테이너
        //@Configuration 사용된 AppConfig의 class를 넘기면
        //@Bean 들을 스프링 컨테이너에 등록하고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //applicationContext.getBean(Bean 이름, type)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //new MemberServiceImpl(memberRepository());

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
