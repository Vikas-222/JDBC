package Exceptions;

public class InvalidOrderException extends Exception{

    //private String msg;

    public InvalidOrderException(String message) {

        super(message);
        //this.msg = message;
    }
}
