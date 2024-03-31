package cars24auction.demo.service;

import cars24auction.demo.dto.AuctionRestartRequest;
import cars24auction.demo.dto.InitiateAuctionRequest;
import cars24auction.demo.entity.Auction;
import cars24auction.demo.entity.Car;
import cars24auction.demo.exception.AuctionNotFoundException;
import cars24auction.demo.exception.CarAlreadyExistException;
import cars24auction.demo.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CustomerInterestService customerInterestService;

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            rollbackFor = CarAlreadyExistException.class)
    public Long startAuction(InitiateAuctionRequest request) {
        Auction auction = request.getAuction();
        auction.setStartTime(new Date());
        auction.setEndTime(getEndDate(auction));
        auction = auctionRepository.save(auction);
        Car car = request.getCar();
        carService.validateCar(car.getNumber());
        Long carId = carService.saveCarInfo(car);
        auction.setCarId(carId);
        auction = auctionRepository.save(auction);
        List<Long> buyerIds = customerInterestService.getDealers(car.getModel(),car.getMake());
        messageService.informInterestedBuyers(buyerIds, auction.getId());
        return auction.getId();
    }

    public Long restartAuction(AuctionRestartRequest request){
        if(isAuctionActive(request.getAuctionId()))
            return request.getAuctionId();
        Optional<Auction> auctionOption = auctionRepository.findById(request.getAuctionId());
        Auction oldAuction = auctionOption.get();
        Auction newAuction = new Auction();
        newAuction.setStartTime(new Date());
        newAuction.setCarId(oldAuction.getCarId());
        newAuction.setStartBid(oldAuction.getStartBid());
        newAuction.setEndTime(getEndDate(newAuction));
        newAuction = auctionRepository.save(newAuction);
        Car car = carService.carInfo(newAuction.getCarId());
        List<Long> buyerIds = customerInterestService.getDealers(car.getModel(),car.getMake());
        messageService.informInterestedBuyers(buyerIds, newAuction.getId());
        return newAuction.getId();
    }

    private static Date getEndDate(Auction auction) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 24);
        Date endDate = calendar.getTime();
        return endDate;
    }

    public Auction getAuctionInfo(Long id){
        Optional<Auction> auctionOptionObj = auctionRepository.findById(id);
        if(!auctionOptionObj.isPresent())
            throw new AuctionNotFoundException(String.format("Auction not found for the auction id %s", id));
        return auctionOptionObj.get();
    }

    public List<Auction> getActiveAuctions(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 24);
        Date endDate = calendar.getTime();
        return auctionRepository.findAllByEndTimeBetween(date, endDate);
    }

    public void updateEndTimeForBidBeforeExpiry(Long auctionId, Date bidTime){
        Optional<Auction> auctionOption = auctionRepository.findById(auctionId);
        if(auctionOption.isPresent()){
            Auction auction = auctionOption.get();
            int secondsInBetween = (int) ((auction.getEndTime().getTime()-bidTime.getTime())/1000);
            if(secondsInBetween<=120){
                Date auctionEndTime = auction.getEndTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(auctionEndTime);
                calendar.add(Calendar.MINUTE, 2);
                Date newEndDate = calendar.getTime();
                auction.setEndTime(newEndDate);
                auctionRepository.save(auction);
            }
        }
    }

    public boolean isAuctionActive(Long auctionId){
        Optional<Auction> auctionOptionObj = auctionRepository.findById(auctionId);
        if(!auctionOptionObj.isPresent())
            throw new AuctionNotFoundException(String.format("Auction not found for the auction id %s", auctionId));
        Date date = new Date();
        return auctionOptionObj.get().getEndTime().after(date);
    }
}
