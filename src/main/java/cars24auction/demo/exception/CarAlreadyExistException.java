package cars24auction.demo.exception;

public class CarAlreadyExistException extends RuntimeException{
    public CarAlreadyExistException(String message){
        super(message);
    }
}
