package hello.core.singleton;

public class StatelessService {
//    private int price; //상태를 유지하는 필드가 존재 (stateful)

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; //문제 코드!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

}
