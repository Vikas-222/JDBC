package Exceptions;

public class StockUnavailableException extends Exception{

        public StockUnavailableException(String error) {
            super(error);
        }
}
