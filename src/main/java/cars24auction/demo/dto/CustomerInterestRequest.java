package cars24auction.demo.dto;

import cars24auction.demo.entity.CustomerInterest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInterestRequest {
    private CustomerInterest customerInterest;
}
