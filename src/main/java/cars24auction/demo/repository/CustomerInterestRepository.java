package cars24auction.demo.repository;

import cars24auction.demo.entity.CustomerInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInterestRepository extends JpaRepository<CustomerInterest, Long> {
    CustomerInterest findByCustomerIdAndModelAndMake(Long customerId, String model, String make);
    List<CustomerInterest> findAllByModelOrMake(String model, String make);
    List<CustomerInterest> findByCustomerId(Long customerId);
}