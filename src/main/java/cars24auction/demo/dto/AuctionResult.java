package cars24auction.demo.dto;

import cars24auction.demo.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionResult {
    private Auction auction;
    private Long highestBid;
    private Long customerId;
}
