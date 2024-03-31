package cars24auction.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Cars24AuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cars24AuctionApplication.class, args);
	}

}
