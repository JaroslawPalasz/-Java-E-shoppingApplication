package pl.polsl.lab.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing basket of a client
 * contains list of products
 * @author Jaroslaw Palasz
 * @version 1.0.0
 */
public class Basket {
    /**
     * List of products in a basket
     */
    private List<Product> productList;
    /**
     * Total cost of products in a basket
     */
    private Double totalCost = 0.0;

    /**
     * Constructor of Basket class, creates empty list of products
     */
    public Basket() {
        productList = new ArrayList<>();
    }

    /**
     * Getter of total cost of products in a basket
     * @return total cost of products from product list
     */
    public Double getTotalCost() { return totalCost; }

    /**
     * Getter of product list
     * @return list of products
     */
    public List<Product> getProductList() { return productList; }

    /**
     * Calculates total cost of all of the products in a product list
     */
    public void calculateTotalCost(){
        totalCost = 0.0;
        if(productList.size() != 0){
            for(int i = 0; i <productList.size(); i++){
                totalCost += productList.get(i).getAmount() * productList.get(i).getPrice();
            }
        }
        else
            totalCost = 0.0;
    }
}