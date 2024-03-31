package cars24auction.demo.controller;

import cars24auction.demo.dto.AuctionRestartRequest;
import cars24auction.demo.dto.AuctionResult;
import cars24auction.demo.dto.InitiateAuctionRequest;
import cars24auction.demo.entity.Auction;
import cars24auction.demo.entity.Bid;
import cars24auction.demo.service.AuctionService;
import cars24auction.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private BidService bidService;

    @PostMapping("/cars24/auctions/start")
    public Long startAuction(@RequestBody InitiateAuctionRequest request){
        return auctionService.startAuction(request);
    }

    @PostMapping("/cars24/auctions/restart")
    public Long restartAuction(@RequestBody AuctionRestartRequest request){
        return auctionService.restartAuction(request);
    }

    @GetMapping("/cars24/auctions/{id}")
    public AuctionResult getAuctionInfo(@PathVariable String id){
        Long auctionId = Long.valueOf(id);
        Auction auction = auctionService.getAuctionInfo(auctionId);
        Bid highestBid = bidService.getHighestBid(auctionId);
        Long highestBidAmount = highestBid==null?auction.getStartBid(): highestBid.getAmount();
        Long customerId = highestBid==null?-1:highestBid.getCustomerId();
        AuctionResult result = null;
        if(auction!=null){
            result = new AuctionResult(auction, highestBidAmount, customerId);
        }
        return result;
    }

    @GetMapping("/cars24/auctions/active")
    public List<Auction> activeAuctions(){
        return auctionService.getActiveAuctions();
    }
}
