package cars24auction.demo.dto;

import cars24auction.demo.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidRequest {
    private Bid bid;
}
