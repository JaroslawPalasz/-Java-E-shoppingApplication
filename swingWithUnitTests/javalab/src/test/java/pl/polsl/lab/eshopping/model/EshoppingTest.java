package pl.polsl.lab.eshopping.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.lab.eshopping.MyException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Eshopping class
 * @author Jaroslaw Palasz
 * @version 1.0.0
 */
class EshoppingTest {
    /**
     * Eshopping field
     */
    Eshopping eshopping;

    /**
     * Creating object of Eshopping class and creating data in constructor before every test,
     * constructor throws exception if incorrect Double price or amount is provided when
     * creating client or product
     */
    @BeforeEach
    void setUp() {
        try{
            eshopping = new Eshopping();
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * After every test eshopping is set to null
     */
    @AfterEach
    void tearDown() {
        eshopping = null;
    }

    /**
     * Test if products, which price is equal or greater to that provided,
     * are found when inputting correct data
     * @param price Double price of product
     * @param number Double number of product
     */
    @ParameterizedTest
    @CsvSource({"0.0, 21.0",
    "2.0, 11.0", "30.0, 1.0"})
    void findProductsWithPriceAtLeastCorrectPrice(Double price, Double number) {
        int foundProducts = 0;
        try{
            foundProducts=eshopping.findProductsWithPriceAtLeast(price).size();
        } catch (MyException myException) {
            myException.printStackTrace();
        }
        assertEquals(foundProducts, number);
    }

    /**
     * Test if products, which price is equal or greater to that provided,
     * are found when inputting negative or NaN value of price
     * @param price Double price of product
     */
    @ParameterizedTest
    @CsvSource({"-3.0", "-4", "NaN"})
    void findProductsWithPriceAtLeastWrongPrice(Double price) {
        try{
            eshopping.findProductsWithPriceAtLeast(price).size();
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * Test if product is found when providing correct data
     * @param productName String name of the product
     * @param productList String name of the list of products
     */
    @ParameterizedTest
    @CsvSource({"c1product1, Category1", "c2product1, Category2",
            "c3product1, Category3"})
    void findProductCorrectParameters(String productName, String productList) {
        try{
            eshopping.findProduct(productName, eshopping.getCategoryList(productList));
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * Test if product is found when providing nonexistent product name or list of products
     * @param productName String name of the product
     * @param productList String name of the list of products
     */
    @ParameterizedTest
    @CsvSource({"product11111111, Category1", "c2product1, wrongcategory",
            "-, !"})
    void findProductWrongParameters(String productName, String productList) {
        try{
            eshopping.findProduct(productName, eshopping.getCategoryList(productList));
        } catch (MyException myException) {
            myException.printStackTrace();
        }
    }

    /**
     * Test if product is added to client's basket, providing that method was given correct parameters
     * @param client String username of client
     */
    @ParameterizedTest
    @ValueSource(strings = {"client1", "client2"})
    void addToBasketCorrectParameters(String client) {
        Product product = null;
        try{
            product = new Product
                    ("Category1", "c1product1",
                            1.0, 3, "description of product1");
        } catch (MyException myException) {
            myException.printStackTrace();
        }
        try{
            eshopping.addToBasket(client, product);
            assertFalse(eshopping.getClientMap().get(client).getBasket().getProductList().isEmpty());
        }catch (MyException myException){
            myException.printStackTrace();
        }
    }

    /**
     * Test if product is added to client's basket, providing that method was given wrong name of client
     * @param client String username of client
     */
    @ParameterizedTest
    @ValueSource(strings = {".", "client3"})
    void addToBasketWrongClient(String client) {
        Product product = null;
        try{
            product = new Product
                    ("Category1", "c1product1",
                            1.0, 3, "description of product1");
        } catch (MyException myException) {
            myException.printStackTrace();
        }
        try{
            eshopping.addToBasket(client, product);
            //assertTrue(eshopping.getClientMap().get(client).getBasket().getProductList().isEmpty());
        }catch (MyException myException){
            myException.printStackTrace();
        }
    }

    /**
     * Test if product is added to client's basket, providing that method was given empty product
     * @param client String username of client
     */
    @ParameterizedTest
    @ValueSource(strings = {"client1", "client2"})
    void addToBasketWrongProduct(String client) {
        Product product = new Product();
        try{
            eshopping.addToBasket(client, product);
        }catch (MyException myException){
            myException.printStackTrace();
        }
    }

    /**
     * Test if index of given product is not null while providing correct data
     * @param productList String name of list of products
     * @param product String name of product
     */
    @ParameterizedTest
    @CsvSource({"Category1,c1product1", "Category1,c1product2",
            "Category2,c2product1", "Category3, c3product1", "Category4, c4product1"})
    void findProductIndexCorrectParameters(String productList, String product) {
        Integer index = eshopping.findProductIndex(eshopping.getCategoryList(productList), product);
        assertNotNull(index);
    }

    /**
     * Test if index of given product is not null while providing invalid product name
     * @param productList String name of list of products
     * @param product String name of product
     */
    @ParameterizedTest
    @CsvSource({"Category1, c2product1", "Category1, x",
            "Category2, d", "Category3, g", "Category4, wrongproduct"})
    void findProductIndexWrongProductName(String productList, String product) {
        Integer index = eshopping.findProductIndex(eshopping.getCategoryList(productList), product);
        assertNull(index);
    }

    /**
     * Test if index of given product is not null while providing invalid name of list of products
     * @param productList String name of list of products
     * @param product String name of product
     */
    @ParameterizedTest
    @CsvSource({"categorywrong, c2product1", "Category1000, c1product2",
            "Category22, c2product1", "Category3363, c3product1", "Category41111, c4product1"})
    void findProductIndexWrongProductList(String productList, String product) {
        Integer index = eshopping.findProductIndex(eshopping.getCategoryList(productList),product);
        assertNull(index);
    }

    /**
     * Test if created ArrayList of strings is not null while providing correct parameters
     * @param productList String list of products
     */
    @ParameterizedTest
    @ValueSource(strings = {"Category1", "Category2", "Category3", "Category4"})
    void convertProductToStringListCorrectParameters(String productList) {
        ArrayList<String> productNameList = new ArrayList<>();
        productNameList = eshopping.convertProductToStringList(
                (ArrayList<Product>) eshopping.getCategoryList(productList),
                productNameList);
        assertNotNull(productNameList);
    }

    /**
     * Test if created ArrayList of strings is null while providing invalid list of products
     * @param productList String list of products
     */
    @ParameterizedTest
    @ValueSource(strings = {"Categorysdsa", "x", "Category33", "aa"})
    void convertProductToStringListWrongProductList(String productList) {
        ArrayList<String> productNameList = new ArrayList<>();
        productNameList = eshopping.convertProductToStringList(
                (ArrayList<Product>) eshopping.getCategoryList(productList),
                productNameList);
        assertNull(productNameList);
    }

    /**
     * Test if created ArrayList of strings is null while providing not empty ArrayList of strings
     * @param productList String list of products
     */
    @ParameterizedTest
    @ValueSource(strings = {"Category1", "Category2", "Category3", "Category4"})
    void convertProductToStringListWrongProductNameList(String productList) {
        ArrayList<String> productNameList = new ArrayList<>();
        productNameList.add("new product");
        productNameList = eshopping.convertProductToStringList(
                (ArrayList<Product>) eshopping.getCategoryList(productList),
                productNameList);
        assertNull(productNameList);
    }

    /**
     * Test if client is logged
     * @param client String username of client
     */
    @ParameterizedTest
    @ValueSource(strings = {"client1", "client2"})
    void returnLoggedUserLogged(String client) {
        eshopping.getClientMap().get(client).setLogged(true);
        assertNotNull(eshopping.returnLoggedUser());
    }

    /**
     * Test if client is not logged
     */
    @Test
    void returnLoggedUserNotLogged() {
        assertNull(eshopping.returnLoggedUser());
    }

    /**
     * Test if user successfully logged in, while providing correct parameters
     * @param login String username of client
     * @param password String password of client
     */
    @ParameterizedTest
    @CsvSource({"client1, 123", "client2, 123"})
    void checkAndReturnLoginCorrectParameters(String login, String password) {
        try{
            eshopping.checkAndReturnLogin(login, password);
        }catch (MyException myException){
            myException.printStackTrace();
        }
    }

    /**
     * Test if user successfully logged in, while providing incorrect password or username
     * @param login String username of client
     * @param password String password of client
     */
    @ParameterizedTest
    @CsvSource({"client111, 123", "client1, 12dsadsa3", "client2, 12dsadsa3", "client2, 123",
            "client2s, 123a"})
    void checkAndReturnLoginWrongPasswordOrUsername(String login, String password) {
        try{
            eshopping.checkAndReturnLogin(login, password);
        }catch (MyException myException){
            myException.printStackTrace();
        }
    }
}