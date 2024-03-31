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
@Table(name = "Inbox")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Long auctionId;
    @CreationTimestamp(source = SourceType.VM)
    private Date creationDate;
    private String content;
}
