package org.simple;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimpleApplication.class)
@WebAppConfiguration
public class SimpleApplicationTests {

	@Test
	public void test() {
		
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject("http://localhost:8080/check", String.class);
		Assert.assertEquals("IMOK",result);
	}

}
