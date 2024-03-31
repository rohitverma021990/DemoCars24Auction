package cars24auction.demo.services;

import cars24auction.demo.entity.Auction;
import cars24auction.demo.exception.AuctionNotFoundException;
import cars24auction.demo.repository.AuctionRepository;
import cars24auction.demo.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {

    @Mock
    private AuctionRepository auctionRepository;
    @InjectMocks
    private AuctionService auctionService;


    @Test
    void getAuctionInfoTest(){
        Auction auction = new Auction(1L, 1L, new Date(), new Date(), 10000L);
        when(auctionRepository.findById(1L)).thenReturn(Optional.of(auction));
        Auction newAuction = auctionService.getAuctionInfo(1L);
        assertEquals(auction, newAuction);
    }

    @Test
    void getAuctionInfoWithExceptionTest(){
        assertThrows(AuctionNotFoundException.class, ()->auctionService.getAuctionInfo(1L));
    }
}
