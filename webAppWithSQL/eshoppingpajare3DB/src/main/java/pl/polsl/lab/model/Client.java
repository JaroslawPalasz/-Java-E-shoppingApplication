/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.model;

/**
 * Class representing client
 */
public class Client {
    /**
     * boolean representing if client is logged, default is not
     */
    private Boolean logged = false;
    /**
     * Username of client
     */
    private String username;
    /**
     * Password of client
     */
    private String password;
    /**
     * Basket of client
     */
    private Basket basket;
    /**
     * Money of client
     */
    private Double money;
    /**
     * Constructor of Client class
     * @param username username of client
     * @param password password of client
     * @param basket basket of client
     * @param money total amount of money held by the client
     */
    public Client(String username, String password, Basket basket, Double money) {
        this.username = username;
        this.password = password;
        this.basket = basket;
        this.money = money;
    }

    /**
     * Getter of username of the client
     * @return username of the client
     */
    public String getUsername() { return username; }

    /**
     * Getter of password of the client
     * @return password of the client
     */
    public String getPassword() { return password; }

    /**
     * Getter of basket of the client
     * @return basket of client
     */
    public Basket getBasket() { return basket; }

    /**
     * Getter of value representing if client is logged
     * @return boolean representing if client is logged
     */
    public Boolean getLogged() { return logged; }

    /**
     * Setter of boolean value representing if client is logge
     * @param logged value representing login status of client
     */
    public void setLogged(Boolean logged) { this.logged = logged; }

    /**
     * Getter of total money of client
     * @return total money of client
     */
    public Double getMoney() { return money; }

    /**
     * Setter of total amount of money of client
     * @param money money of client
     */
    public void setMoney(Double money) { this.money = money; }

}

