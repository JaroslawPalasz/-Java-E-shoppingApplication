package pl.polsl.lab;

/**
 * Custom exception class
 * @author Jaroslaw Palasz
 * @version 1.0
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
