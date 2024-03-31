package cars24auction.demo.controller;

import cars24auction.demo.dto.CustomerInfoRequest;
import cars24auction.demo.dto.CustomerInfoWithInterestResponse;
import cars24auction.demo.dto.CustomerInterestRequest;
import cars24auction.demo.entity.Customer;
import cars24auction.demo.entity.CustomerInterest;
import cars24auction.demo.service.CustomerInterestService;
import cars24auction.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerInterestService customerInterestService;

    @PostMapping("/cars24/customers")
    public Long saveCustomerInfo(@RequestBody CustomerInfoRequest request){
        return customerService.saveCustomerInfo(request);
    }

    @GetMapping("/cars24/customers/{id}")
    public CustomerInfoWithInterestResponse customerInfo(@PathVariable String id){
        Long customerId = Long.valueOf(id);
        Customer customer = customerService.getInfo(customerId);
        List<CustomerInterest> customerInterests = customerInterestService.getCustomerInterestForCustomerId(customerId);
        return new CustomerInfoWithInterestResponse(customer, customerInterests);
    }

    @PutMapping("/cars24/customers/interest")
    public Long addInterest(@RequestBody CustomerInterestRequest request){
        return customerInterestService.addInterest(request);
    }
}
