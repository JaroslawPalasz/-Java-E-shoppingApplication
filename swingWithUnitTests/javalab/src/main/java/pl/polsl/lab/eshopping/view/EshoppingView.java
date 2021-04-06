package pl.polsl.lab.eshopping.view;

//import pl.polsl.lab.eshopping.model.ButtonClickListener;

import pl.polsl.lab.eshopping.model.Basket;
import pl.polsl.lab.eshopping.model.Client;
import pl.polsl.lab.eshopping.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class representing login gui
 * Holds other gui objects
 */
public class EshoppingView extends JFrame{
    /**
     * CategoryView gui
     */
    private CategoryView categoryView;
    /**
     * ProductList gui
     */
    private ProductListView productListView;
    /**
     * Basket gui
     */
    private BasketView basketView;

    /**
     * Main panel of gui
     */
    private JPanel panel1;
    /**
     * Login button
     */
    private JButton loginButton;
    /**
     * TextField to type in username
     */
    private JTextField textField1;
    /**
     * TextField to type in password
     */
    private JPasswordField passwordField1;

    /**
     * Getter of login button
     * @return login button
     */
    public JButton getLoginButton() { return loginButton; }

    /**
     * Getter of textfield
     * @return textfield
     */
    public JTextField getTextField1() { return textField1; }

    /**
     * Getter of passwordfield
     * @return passwordField
     */
    public JPasswordField getPasswordField1() { return passwordField1; }

    /**
     * Getter of categoryView
     * @return categoryView object
     */
    public CategoryView getCategoryView() { return categoryView; }

    /**
     * Getter of productView
     * @return productView object
     */
    public ProductListView getProductListView() { return productListView; }

    /**
     * Getter of basketView
     * @return basketView object
     */
    public BasketView getBasketView() { return basketView; }


    /**
     * Constructor of EshoppingView class
     * @param categoryView categoryView object
     * @param productListView productListView object
     * @param basketView basketView object
     */
    public EshoppingView(CategoryView categoryView, ProductListView productListView, BasketView basketView) {
        this.categoryView = categoryView;
        this.productListView = productListView;
        this.basketView = basketView;
        loginView();
    }

    /**
     * Sets panel to this of login view
     * Sets all of the buttons and other entities
     */
    public void loginView(){
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panel1);
        //this.setContentPane(panel1);
        textField1.setText("");
        passwordField1.setText("");
        loginButton.setActionCommand("Login");
        this.setTitle("Login");
        this.pack();
        this.setSize(600,600);

        this.setVisible(true);
    }

    /**
     * Sets panel to this of categoryView
     * Sets all the buttons and other entities
     * Shows which client is logged in
     * @param loggedUser username of client which is logged in
     */
    public void categoryView(String loggedUser){
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(categoryView.getPanel1());
        categoryView.getLogout().setActionCommand("Logout");
        categoryView.getCategory1().setActionCommand("Category1");
        categoryView.getCategory2().setActionCommand("Category2");
        categoryView.getCategory3().setActionCommand("Category3");
        categoryView.getCategory4().setActionCommand("Category4");
        categoryView.getBack().setActionCommand("Back");
        categoryView.getLoggedin().setText(loggedUser);
        this.setTitle("CategoryView");
        this.pack();
        this.setSize(600,600);
        this.setVisible(true);
    }

    /**
     * Sets panel to this of productListView
     * Sets all the buttons and other entities
     * Sets ListModel and selectionModel
     * @param productList list of products from given category
     * @param money amount of money of client
     * @param loggedUser username of logged client
     * @param category category of list of products
     */
    public void productListView(ArrayList<String> productList,
                                Double money, String loggedUser, String category){
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(productListView.getPanel());
        productListView.getBack().setActionCommand("BackToCategoryView");
        productListView.getBasket().setActionCommand("Basket");
        productListView.getLogoutButton().setActionCommand("Logout");
        productListView.getAddToBasket().setActionCommand("AddToBasket");

        productListView.getCategory().setText(category);
        productListView.getLoggedin().setText(loggedUser);
        productListView.getPln().setText("Pln: " + money.toString());

        productListView.getAddToBasket().setEnabled(false);

        productListView.getProductList().setListData(productList.toArray());
        productListView.getProductList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setTitle("ProductListView");
        this.pack();
        this.setSize(600,600);
        this.setVisible(true);
    }

    /**
     * Sets panel to this of basketView
     * Sets all the buttons and other entities
     * Sets ListModel and selectionModel
     * @param productList list of products of client
     * @param money money of client
     * @param loggedUser username of client which is logged in
     * @param category name of category chosen before moving to basket
     * @param totalCost total cost of items in the basket
     */
    public void basketView(ArrayList<String> productList,
                           Double money, String loggedUser, String category, Double totalCost){
        this.getContentPane().removeAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(basketView.getPanel());

        basketView.getBack().setActionCommand("BackToProductView");
        basketView.getLogoutButton().setActionCommand("Logout");
        basketView.getRemoveFromBasket().setActionCommand("RemoveFromBasket");
        basketView.getPayButton().setActionCommand("Pay");

        basketView.getLoggedin().setText(loggedUser);
        basketView.getPln().setText("Pln: " + money.toString());
        basketView.getProductCategory().setText("Category: ");
        basketView.getPrice().setText("Price: ");
        basketView.getAmount().setText("Amount: ");
        basketView.getTotalCost().setText("Total cost: " + totalCost);

        basketView.setPreviousProductListCategory(category);
        basketView.getRemoveFromBasket().setEnabled(false);
        basketView.getPayButton().setEnabled(true);

        basketView.getList1().setListData(productList.toArray());

        basketView.getList1().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setTitle("BasketView");
        this.pack();
        this.setSize(600,600);
        this.setVisible(true);
    }
}
