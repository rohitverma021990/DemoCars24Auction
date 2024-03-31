package cars24auction.demo.services;

import cars24auction.demo.entity.Bid;
import cars24auction.demo.repository.BidRepository;
import cars24auction.demo.service.BidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BidServiceTest {

    @Mock
    private BidRepository bidRepository;

    @InjectMocks
    private BidService bidService;

    @Test
    void getHighestBidTest(){
        Bid bid = new Bid(1L, 1L, 1L, 10000L, new Date(), new Date());
        when(bidRepository.findByAuctionIdOrderByAmountDesc(1L)).thenReturn(List.of(bid));
        Bid highestBid = bidService.getHighestBid(1L);
        assertEquals(bid, highestBid);
    }

    @Test
    void getBidsTest(){
        Bid bid1 = new Bid(1L, 1L, 1L, 10000L, new Date(), new Date());
        Bid bid2 = new Bid(1L, 1L, 1L, 12000L, new Date(), new Date());
        when(bidRepository.findAllByAuctionIdOrderByAmountDesc(1L)).thenReturn(List.of(bid1, bid2));
        List<Bid> bids = bidService.getBids(1L);
        assertEquals(bids.size(),2);
    }
}
