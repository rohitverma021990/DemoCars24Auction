package cars24auction.demo.repository;

import cars24auction.demo.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByEndTimeBetween(Date startTime, Date endTime);
}
