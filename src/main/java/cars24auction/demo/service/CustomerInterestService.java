package cars24auction.demo.service;

import cars24auction.demo.dto.CustomerInterestRequest;
import cars24auction.demo.entity.CustomerInterest;
import cars24auction.demo.repository.CustomerInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerInterestService {

    @Autowired
    private CustomerInterestRepository customerInterestRepository;

    public Long addInterest(CustomerInterestRequest request){
        CustomerInterest interest = request.getCustomerInterest();
        CustomerInterest existingInterest = customerInterestRepository.
                findByCustomerIdAndModelAndMake(interest.getCustomerId(),
                        interest.getModel(), interest.getMake());
        if(existingInterest!=null)
            return existingInterest.getId();
        interest = customerInterestRepository.save(interest);
        return interest.getId();
    }

    public List<Long> getDealers(String model, String make){
        List<CustomerInterest> customerInterests = customerInterestRepository.findAllByModelOrMake(model, make);
        Set<Long> customerIds = customerInterests.stream().map(CustomerInterest::getCustomerId).collect(Collectors.toSet());
        return new ArrayList<>(customerIds);
    }

    public List<CustomerInterest> getCustomerInterestForCustomerId(Long customerId){
        return customerInterestRepository.findByCustomerId(customerId);
    }


}
