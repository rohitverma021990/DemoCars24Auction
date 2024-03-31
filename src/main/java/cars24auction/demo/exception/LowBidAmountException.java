package cars24auction.demo.exception;

public class LowBidAmountException extends RuntimeException{
    public LowBidAmountException(String message){
        super(message);
    }
}
