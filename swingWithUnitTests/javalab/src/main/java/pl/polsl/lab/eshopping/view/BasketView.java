package pl.polsl.lab.eshopping.view;

import javax.swing.*;

/**
 * Class representing view of basket
 */
public class BasketView {
    /**
     * Logout button
     */
    private JButton logoutButton;
    /**
     * Back button
     */
    private JButton back;
    /**
     * list of products in client's basket put in a scroll pane
     */
    private JList list1;
    /**
     * Pay button
     */
    private JButton payButton;
    /**
     * Label representing who is logged in
     */
    private JLabel loggedin;
    /**
     * Label representing amount of money of client logged in
     */
    private JLabel pln;
    /**
     * Button removing items from basket
     */
    private JButton removeFromBasket;
    /**
     * Label representing total cost of all of the products in a basket
     */
    private JLabel totalCost;
    /**
     * Main panel of basket gui
     */
    private JPanel panel;
    /**
     * Label representing category of chosen product from a list
     */
    private JLabel productCategory;
    /**
     * Label representing price of chosen product from a list
     */
    private JLabel price;
    /**
     * Label representing amount of chosen product from a list
     */
    private JLabel amount;
    /**
     * Name of category chosen before moving to basket, used with "Back" button
     */
    private String previousProductListCategory;

    /**
     * Getter of product category label
     * @return product category label
     */
    public JLabel getProductCategory() { return productCategory; }

    /**
     * Getter of price of the product label
     * @return product price label
     */
    public JLabel getPrice() { return price; }

    /**
     * Getter of amount of product label
     * @return amount of chosen product label
     */
    public JLabel getAmount() { return amount; }

    /**
     * Getter of category chosen before moving to basket view
     * @return category name
     */
    public String getPreviousProductListCategory() { return previousProductListCategory; }

    /**
     * Setter of category chosen before moving to basket view
     * @param previousProductListCategory category name
     */
    public void setPreviousProductListCategory(String previousProductListCategory) {
        this.previousProductListCategory = previousProductListCategory; }

    /**
     * Getter of main panel of basket gui
     * @return main panel of basket gui
     */
    public JPanel getPanel() { return panel; }

    /**
     * Getter of button removing product from basket
     * @return button removeFromBasket
     */
    public JButton getRemoveFromBasket() { return removeFromBasket; }

    /**
     * Getter of label representing total cost of items in the basket
     * @return totalCost label
     */
    public JLabel getTotalCost() { return totalCost; }

    /**
     * Getter of logout button
     * @return logout button
     */
    public JButton getLogoutButton() { return logoutButton; }

    /**
     * Getter of back button
     * @return back button
     */
    public JButton getBack() { return back; }

    /**
     * Getter of list of products in a basket
     * @return list of products in a basket
     */
    public JList getList1() { return list1; }

    /**
     * Getter of pay button
     * @return pay button
     */
    public JButton getPayButton() { return payButton; }

    /**
     * Getter of label representing logged client
     * @return loggedIn label
     */
    public JLabel getLoggedin() { return loggedin; }

    /**
     * Getter of label representing total amount of money of client
     * @return getPln label
     */
    public JLabel getPln() { return pln; }
}
