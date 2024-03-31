package cars24auction.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER_INTEREST")
public class CustomerInterest {
    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String make;
    private Long customerId;

}
