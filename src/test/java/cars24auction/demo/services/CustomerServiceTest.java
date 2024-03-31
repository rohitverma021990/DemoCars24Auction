package cars24auction.demo.services;

import cars24auction.demo.dto.CustomerInfoRequest;
import cars24auction.demo.entity.Customer;
import cars24auction.demo.repository.CustomerRepository;
import cars24auction.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void saveCustomerInfoTest(){
        Customer customer = new Customer(1L,"TU1@gmail.com", "TestUser1");
        CustomerInfoRequest request = new CustomerInfoRequest(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        customerService.saveCustomerInfo(request);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getCustomerInfoTest() {
        Customer customer = new Customer(1L, "TU1@gmail.com", "TestUser1");
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        Customer newCustomer = customerService.getInfo(1L);
        verify(customerRepository, times(1)).findById(1L);
    }
}
