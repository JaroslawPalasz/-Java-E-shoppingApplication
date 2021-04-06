package pl.polsl.lab.eshopping.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.eshopping.MyException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Product class
 * @author Jaroslaw Palasz
 * @version 1.0.0
 */
class ProductTest {

    /**
     * Test if exception is thrown when provided correct data
     * @param price Double price of product
     * @param amount Double amount of product
     */
    @ParameterizedTest
    @CsvSource({"4.0, 3", "3.666666, 33"})
    void productConstructorTestCorrectParameters(Double price, Double amount) {
        try{
            new Product("category", "name", price, amount.intValue(), "description");
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * Test if exception is thrown when provided negative or NaN values of price and money
     * @param price Double price of product
     * @param amount Double amount of product
     */
    @ParameterizedTest
    @CsvSource({"-4.0, 3", "NaN, 33", "5.0, -3"})
    void productConstructorTestWrongParameters(Double price, Double amount) {
        try{
            new Product("category", "name", price, amount.intValue(), "description");
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }
}