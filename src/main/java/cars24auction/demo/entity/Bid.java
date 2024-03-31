package cars24auction.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BID_INFO")
public class Bid {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Long auctionId;
    private Long amount;
    @CreationTimestamp(source = SourceType.DB)
    private Date bidDate;
    @UpdateTimestamp(source = SourceType.DB)
    private Date updateDate;
}
