package cars24auction.demo.dto;

import cars24auction.demo.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoRequest {
    private Car carInfo;
}
