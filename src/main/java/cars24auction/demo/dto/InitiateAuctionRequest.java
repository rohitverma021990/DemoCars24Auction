package cars24auction.demo.dto;

import cars24auction.demo.entity.Auction;
import cars24auction.demo.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitiateAuctionRequest {
    private Auction auction;
    private Car car;
}
