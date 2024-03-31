package cars24auction.demo;

import cars24auction.demo.dto.CustomerInfoRequest;
import cars24auction.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Cars24AuctionApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddCustomer(){
		Customer customer = new Customer();
		customer.setName("TestUser");
		customer.setEmailId("test@gmail.com");
		CustomerInfoRequest request = new CustomerInfoRequest(customer);

	}

}
