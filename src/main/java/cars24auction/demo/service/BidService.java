package cars24auction.demo.service;

import cars24auction.demo.dto.BidRequest;
import cars24auction.demo.dto.BidResponse;
import cars24auction.demo.entity.Auction;
import cars24auction.demo.entity.Bid;
import cars24auction.demo.exception.LowBidAmountException;
import cars24auction.demo.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionService auctionService;

    public BidResponse addBid(BidRequest request){
        BidResponse response = new BidResponse("");
        Bid bid = request.getBid();
        validateBid(bid);
        if(!auctionService.isAuctionActive(bid.getAuctionId())){
            response.setMessage(String.format("Auction %s is completed so bidding is not possible", bid.getAuctionId()));
            return response;
        }
        Bid oldBid = bidRepository.findByAuctionIdAndCustomerId(bid.getAuctionId(), bid.getCustomerId());
        if(oldBid!=null){
            oldBid.setAmount(bid.getAmount());
            bid = oldBid;
            response.setMessage(String.format("Bid %s updated successfully", bid.getId()));
        }
        bid = bidRepository.save(bid);

        if(oldBid==null)
            response.setMessage(String.format("Bid %s Added successfully", bid.getId()));
        auctionService.updateEndTimeForBidBeforeExpiry(bid.getAuctionId(), bid.getUpdateDate());
        return response;
    }

    private void validateBid(Bid bid) {
        Bid highestBid = getHighestBid(bid.getAuctionId());
        if(highestBid==null){
            Auction auction = auctionService.getAuctionInfo(bid.getAuctionId());
            if(bid.getAmount()<auction.getStartBid())
                throw new LowBidAmountException(String.format("Bid Amount %s is less than Auction Start Amount : %s", bid.getAmount(), auction.getStartBid()));
        }
        else if(bid.getAmount()<=highestBid.getAmount())
            throw new LowBidAmountException(String.format("Bid Amount %s is less than Highest Bid Amount : %s", bid.getAmount(), highestBid.getAmount()));
    }

    public List<Bid> getBids(Long auction_id){
        return bidRepository.findAllByAuctionIdOrderByAmountDesc(auction_id);
    }

    public Bid getHighestBid(Long auctionId){
        List<Bid> bids = bidRepository.findByAuctionIdOrderByAmountDesc(auctionId);
        return CollectionUtils.isEmpty(bids) ?null:bids.get(0);
    }

}
