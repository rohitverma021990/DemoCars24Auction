package cars24auction.demo.exception;

import cars24auction.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = CarAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleCarAlreadyExistException(CarAlreadyExistException ex){
        return new ErrorResponse(400, ex.getMessage());
    }

    @ExceptionHandler(value = LowBidAmountException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public @ResponseBody ErrorResponse handleLowBidAmountException(LowBidAmountException ex){
        return new ErrorResponse(400, ex.getMessage());
    }

    @ExceptionHandler(value = AuctionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleAuctionNotFoundException(AuctionNotFoundException ex){
        return new ErrorResponse(400, ex.getMessage());
    }
}
