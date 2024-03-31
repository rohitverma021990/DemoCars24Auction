package cars24auction.demo.repository;

import cars24auction.demo.entity.Bid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findAllByAuctionIdOrderByAmountDesc(Long auctionId);
    List<Bid> findByAuctionIdOrderByAmountDesc(Long auctionId);
    Bid findByAuctionIdAndCustomerId(Long auctionId, Long customerId);
}
