package cars24auction.demo.controller;

import cars24auction.demo.dto.BidRequest;
import cars24auction.demo.dto.BidResponse;
import cars24auction.demo.entity.Bid;
import cars24auction.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/cars24/bids")
    public BidResponse addBid(@RequestBody BidRequest request){
        return bidService.addBid(request);
    }

    @GetMapping("/cars24/bids/{id}")
    public List<Bid> bidsForAuction(@PathVariable String id){
        Long auctionId = Long.valueOf(id);
        return bidService.getBids(auctionId);
    }
}
