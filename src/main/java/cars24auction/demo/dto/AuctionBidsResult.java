package cars24auction.demo.dto;

import cars24auction.demo.entity.Auction;
import cars24auction.demo.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionBidsResult {
    private Auction auction;
    private List<Bid> bids;
}
