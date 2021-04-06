package pl.polsl.lab.eshopping;

/**
 * Custom exception class
 */
public class MyException extends Exception{
    /**
     * Constructor of custom exception class
     * displays given message
     * @param message sent message when exception is occuring
     */
    public MyException(String message){
        super(message);
    }
}
