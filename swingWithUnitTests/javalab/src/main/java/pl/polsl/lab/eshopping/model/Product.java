package pl.polsl.lab.eshopping.model;

import pl.polsl.lab.eshopping.MyException;

/**
 * Class representing single product
 */
public class Product {
    /**
     * Category of product
     */
    private String category;
    /**
     * Name of product
     */
    private String name;
    /**
     * Price of product
     */
    private Double price;
    /**
     * Amount of product
     */
    private Integer amount;
    /**
     * Description of product
     */
    private String description;

    /**
     * Constructor of Product class, uses checkdata method to verify user input
     * @param category category of product
     * @param name name of product
     * @param price price of product
     * @param amount amount of product
     * @param description description of product
     * @throws MyException if incorrect price or amount is given
     */
    public Product(String category, String name, Double price, Integer amount, String description) throws MyException {
        checkdata(price, amount);
        this.category = category;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public Product() {
    }

    /**
     * checks if price and amount are valid numbers (positive, not NaN)
     * @param price price of product
     * @param amount amount of product
     * @throws MyException if incorrect price or amount is given
     */
    public void checkdata(Double price, Integer amount) throws MyException {
        if(price.isNaN() || price.isInfinite() || price < 0.0)
            throw new MyException("Price is incorrect");
        else if (amount < 0)
            throw new MyException("Amount is incorrect");
    }
    /**
     * Getter of product's category
     * @return category of product
     */
    public String getCategory() { return category; }

    /**
     * Getter of name of the product
     * @return name of the product
     */
    public String getName() { return name; }

    /**
     * Getter of price of the produc
     * @return price of the product
     */
    public Double getPrice() { return price; }

    /**
     * Getter of amount of the product
     * @return amount of product
     */
    public Integer getAmount() { return amount; }

    /**
     * Setter of amount on product
     * @param amount new amount of product
     */
    public void setAmount(Integer amount) { this.amount = amount; }

    /**
     * Getter of description of the product
     * @return description of the product
     */
    public String getDescription() { return description; }
}
