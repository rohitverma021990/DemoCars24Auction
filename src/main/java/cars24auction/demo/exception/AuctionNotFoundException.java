package cars24auction.demo.exception;

public class AuctionNotFoundException extends RuntimeException{
    public AuctionNotFoundException(String message){
        super(message);
    }
}
