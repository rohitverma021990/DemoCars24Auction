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
@Table(name = "AUCTION_INFO")
public class Auction {
    @Id
    @GeneratedValue
    private Long id;
    private Long carId;
    private Date startTime;
    private Date endTime;
    private Long startBid;
}
