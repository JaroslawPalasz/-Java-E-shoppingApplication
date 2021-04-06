package pl.polsl.lab.eshopping.view;

import javax.swing.*;

/**
 * Class representing view of product list
 */
public class ProductListView {
    /**
     * List of products from given category
     */
    private JList productList;
    /**
     * Logout button
     */
    private JButton logoutButton;
    /**
     * Basket button
     */
    private JButton basket;
    /**
     * Back button
     */
    private JButton back;
    /**
     * Label representing amount of money of client
     */
    private JLabel pln;
    /**
     * Main panel of productListView gui
     */
    private JPanel panel;
    /**
     * Scroll pane
     */
    private JScrollPane scrollPane;
    /**
     * AddToBasket button
     */
    private JButton addToBasket;
    /**
     * Label representing how many items are left
     */
    private JLabel amountLeft;
    /**
     * Label representing price of an item
     */
    private JLabel price;
    /**
     * Label showing which client is logged in
     */
    private JLabel loggedin;
    /**
     * Label representing category of chosen product
     */
    private JLabel category;

    /**
     * Getter of category of product label
     * @return category label
     */
    public JLabel getCategory() { return category; }

    /**
     * Getter of addToBasket button
     * @return addToBasket button
     */
    public JButton getAddToBasket() { return addToBasket; }

    /**
     * Getter of amountLeft button
     * @return amountLeft button
     */
    public JLabel getAmountLeft() { return amountLeft; }

    /**
     * Getter of label showing price of an item
     * @return price label
     */
    public JLabel getPrice() { return price; }

    /**
     * Getter of label representing amount of money of client
     * @return pln label
     */
    public JLabel getPln() { return pln; }

    /**
     * Getter of label showing which client is logged in
     * @return loggedin label
     */
    public JLabel getLoggedin() { return loggedin; }

    /**
     * Getter of panel of productListView gui
     * @return panel of productListView
     */
    public JPanel getPanel() { return panel; }

    /**
     * Getter of list of products in a category
     * @return list of products in a category
     */
    public JList getProductList() { return productList; }

    /**
     * Getter of logout button
     * @return logout button
     */
    public JButton getLogoutButton() { return logoutButton; }

    /**
     * Getter of basket button
     * @return basket button
     */
    public JButton getBasket() { return basket; }

    /**
     * Getter of back button
     * @return back button
     */
    public JButton getBack() { return back; }
}
