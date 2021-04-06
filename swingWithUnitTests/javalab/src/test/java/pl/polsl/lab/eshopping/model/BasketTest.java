package pl.polsl.lab.eshopping.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.eshopping.MyException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Basket class
 * @author Jaroslaw Palasz
 * @version 1.0.0
 */
class BasketTest {
    /**
     * Basket field
     */
    Basket basket;

    /**
     * Creates new basket before every test
     */
    @BeforeEach
    void setUp() {
        basket = new Basket();
    }

    /**
     * After every test basket is set to null
     */
    @AfterEach
    void tearDown() {
        basket = null;
    }

    /**
     * Calculating total cost of products in a basket with correct price and amount
     * @param price price of first product
     * @param amount amount of first product
     * @param price2 price of second product
     * @param amount2 amount of second product
     * @param price3 price of third product
     * @param amount3 amount of third product
     * @param totalCost expected total cost of products in a basket
     */
    @ParameterizedTest
    @CsvSource({"1.0,3.0, 999.0,99.0, 0.0,1.0, 98904.0",
    "2.5,3.0, 1.0,0.0, 0.0,0.0, 7.5"})
    void calculateTotalCostCorrectParameters(Double price, Double amount,
                                             Double price2, Double amount2,
                                             Double price3, Double amount3,
                                             Double totalCost) {
        try{
            basket.setProductList(Arrays.asList(
                    new Product("Category1", "c1product1",
                            price, amount.intValue(), "description of product1"),
                    new Product("Category1", "c1product2",
                            price2, amount2.intValue(), "description of product2"),
                    new Product("Category1", "c1product3",
                            price3, amount3.intValue(), "description of product3")
            ));
        } catch (MyException myException) {
            myException.printStackTrace();
        }
        basket.calculateTotalCost();
        assertEquals(basket.getTotalCost(), totalCost.doubleValue());
    }

    /**
     * Calculating total cost of products in a basket with incorrect price and amount
     * NaN, negative values
     * @param price price of first product
     * @param amount amount of first product
     * @param price2 price of second product
     * @param amount2 amount of second product
     * @param totalCost expected total cost of products in a basket
     */
    @ParameterizedTest
    @CsvSource({"-2.0,3.0, 3.0,-4.0, NaN"})
    void calculateTotalCostWrongPrice(Double price, Double amount,
                                      Double price2, Double amount2,
                                      Double totalCost) {
        try{
            basket.setProductList(Arrays.asList(
                    new Product(null, "c1product1",
                            price, amount.intValue(), "description of product1"),
                    new Product("Category1", "c1product1",
                            price2, amount2.intValue(), "description of product1")
            ));
        } catch (MyException myException) {
            myException.printStackTrace();
        }
        basket.calculateTotalCost();
        //assertEquals(basket.getTotalCost(), totalCost.doubleValue());
    }
}
