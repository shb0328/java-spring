package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	// 필드 주입을 사용해도 되는 경우 1) 테스트코드
	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads() {
	}

}
