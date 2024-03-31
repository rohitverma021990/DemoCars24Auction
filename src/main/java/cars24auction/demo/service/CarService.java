package cars24auction.demo.service;

import cars24auction.demo.entity.Car;
import cars24auction.demo.exception.CarAlreadyExistException;
import cars24auction.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Long saveCarInfo(Car car){
        car = carRepository.save(car);
        return car.getId();
    }

    public Car carInfo(Long id){
        return carRepository.findById(id).get();
    }

    public Car carInfo(String number){
        return carRepository.findByNumber(number);
    }

    public void validateCar(String number){
        Car car = carRepository.findByNumber(number);
        if(car != null){
            throw new CarAlreadyExistException("Car already existing in system with number: "+ number);
        }
    }
}
