/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.myexception;

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
