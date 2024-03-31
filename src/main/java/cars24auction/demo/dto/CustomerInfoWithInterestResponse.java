package cars24auction.demo.dto;

import cars24auction.demo.entity.Customer;
import cars24auction.demo.entity.CustomerInterest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoWithInterestResponse {
    private Customer customer;
    private List<CustomerInterest> customerInterests;
}
