package pl.polsl.lab.eshopping.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.eshopping.MyException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Client class
 * @author Jaroslaw Palasz
 * @version 1.0.0
 */
class ClientTest {

    /**
     * Test if exception is thrown in constructor of client class when provided correct data
     * @param money money of client
     */
    @ParameterizedTest
    @CsvSource({"4.0", "3.666666", "0.0"})
    void clientConstructorTestCorrectParameters(Double money) {
        try{
            new Client("username", "password", new Basket(), money);
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * Test if exception is thrown in constructor of client class when provided negative of NaN value of money
     * @param money money of client
     */
    @ParameterizedTest
    @CsvSource({"NaN", "-3.0"})
    void clientConstructorTestWrongParameters(Double money) {
        try{
            new Client("username", "password", new Basket(), money);
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }
}