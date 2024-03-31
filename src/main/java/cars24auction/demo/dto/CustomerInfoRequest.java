package cars24auction.demo.dto;

import cars24auction.demo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoRequest {
    private Customer customer;
}
