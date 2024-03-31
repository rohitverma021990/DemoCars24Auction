package cars24auction.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CAR_INFO")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String make;
    private String model;
    private String variant;
    private int manufacturingYear;
    private int registrationYear;
    private String number;
    @CreationTimestamp(source = SourceType.VM)
    private Date creationDate;

}
