/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.model;

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
     * Constructor of Product class
     * @param category category of product
     * @param name name of product
     * @param price price of product
     * @param amount amount of product
     * @param description description of product
     */
    public Product(String category, String name, Double price, Integer amount, String description) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
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

