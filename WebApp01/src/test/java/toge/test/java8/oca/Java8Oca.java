package toge.test.java8.oca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:config/test-config.xml")
public class Java8Oca {

	@Test
	public void test(){
		System.out.println("test java 8 OCA");
	}

	@Test
	public void literal(){
System.out.println("test java 8 OCA123456");
//		long max = 3123456789;
	
	
	}
	
}
