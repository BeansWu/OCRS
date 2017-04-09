package ocrs.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ocrs.service.UserService;

public class TestService {

	@Test
	public void testLoginCheck() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ctx.getBean(UserService.class);
		System.out.println(userService.loginCheck("Sam", "12345"));
		ctx.close();
	}

}
