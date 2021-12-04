package hello.core.singleton;

//스프링컨테이너가 default로 Bean을 Singleton으로 생성해서 관리해주므로 직접 Singleton 코드를 작성할 필요가 없다.
public class SingletonService {

    private int value;
    private static final SingletonService instance = new SingletonService(); //자기자신을 private static final instance 로 스스로 생성해서 static 영역에 하나만 가지고 있는다.

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        value = 0;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
        value++;
        System.out.println("value = " + value);
    }
}
