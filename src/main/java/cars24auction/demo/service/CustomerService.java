package cars24auction.demo.service;

import cars24auction.demo.dto.CustomerInfoRequest;
import cars24auction.demo.entity.Customer;
import cars24auction.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Long saveCustomerInfo(CustomerInfoRequest request){
        Customer customer = request.getCustomer();
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    public Customer getInfo(Long customerId){
        return customerRepository.findById(customerId).get();
    }
}
