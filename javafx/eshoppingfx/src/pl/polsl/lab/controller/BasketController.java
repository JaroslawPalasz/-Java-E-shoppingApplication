package pl.polsl.lab.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.polsl.lab.MyException;
import pl.polsl.lab.model.Eshopping;
import pl.polsl.lab.model.Product;
import pl.polsl.lab.view.AlertManagement;

import java.io.IOException;
import java.util.Objects;

/**
 * Class of controller of client's basket
 * @author Jaroslaw Palasz
 * @version 1.0
 */
public class BasketController {

    /**
     * Model object containing all of the data
     */
    private Eshopping eshopping;

    /**
     * Manager of alerts
     */
    private AlertManagement alertManagement;

    /**
     * Name of previously selected product list
     */
    private String previousProductListCategory;

    /**
     * Logout button
     */
    @FXML
    private Button logoutButton;
    /**
     * Back button
     */
    @FXML
    private Button backButton;
    /**
     * Button removing products from basket
     */
    @FXML
    private Button removeFromBasketButton;
    /**
     * Pay button
     */
    @FXML
    private Button payButton;

    /**
     * Label showing who is currently logged in
     */
    @FXML
    private Label loggedLabel;
    /**
     * Label showing total cost of products
     */
    @FXML
    private Label totalCostLabel;
    /**
     * Label showing total amount of client's money
     */
    @FXML
    private Label plnLabel;

    /**
     * Table representing basket of client
     */
    @FXML
    private TableView<Product> basketTableView;
    /**
     * Name column
     */
    @FXML
    private TableColumn<Product, String> tableColumnName;
    /**
     * Price column
     */
    @FXML
    private TableColumn<Product, Double> tableColumnPrice;
    /**
     * Amount column
     */
    @FXML
    private TableColumn<Product, Integer> tableColumnAmount;
    /**
     * Description column
     */
    @FXML
    private TableColumn<Product, String> tableColumnDescription;
    /**
     * Category column
     */
    @FXML
    private TableColumn<Product, String> tableColumnCategory;

    /**
     * Getter of model
     * @return eshopping object
     */
    public Eshopping getEshopping() { return eshopping; }

    /**
     * Setter of model
     * @param eshopping eshopping object
     */
    public void setEshopping(Eshopping eshopping) { this.eshopping = eshopping; }

    /**
     * Getter of name of previously selected product list
     * @return name of previously selected product list
     */
    public String getPreviousProductListCategory() { return previousProductListCategory; }

    /**
     * Setter of previously selected product list
     * @param previousCategory name of previously selected product list
     */
    public void setPreviousProductListCategory(String previousCategory) {
        this.previousProductListCategory = previousCategory; }

    /**
     * Initializes the controller, sets the name of previously selected product list
     * sets the model, basket table and all of the labels
     * @param eshopping eshopping object
     * @param productList name of previously selected product list
     */
    public void init(Eshopping eshopping, String productList){
        alertManagement = new AlertManagement();
        setEshopping(eshopping);
        setPreviousProductListCategory(productList);
        loggedLabel.setText(eshopping.returnLoggedUser());

        eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().calculateTotalCost();
        totalCostLabel.setText("Total cost: " + eshopping.getClientMap().get
                (eshopping.returnLoggedUser()).getBasket().getTotalCost().toString());
        plnLabel.setText("PLN: "
                + eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney().toString());

        tableColumnName.setCellValueFactory
                (param -> new SimpleStringProperty(param.getValue().getName()));
        tableColumnPrice.setCellValueFactory
                (param -> new SimpleDoubleProperty(param.getValue().getPrice()).asObject());
        tableColumnAmount.setCellValueFactory
                (param -> new SimpleIntegerProperty
                        (param.getValue().getAmount()).asObject());
        tableColumnDescription.setCellValueFactory
                (param -> new SimpleStringProperty
                        (param.getValue().getDescription()));
        tableColumnCategory.setCellValueFactory
                (param -> new SimpleStringProperty(param.getValue().getCategory()));
        basketTableView.getItems().setAll
                (eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList());

    }

    /**
     * Goes back to where the user previously were
     * Used by backButton
     * @param actionEvent button click
     */
    @FXML
    private void back(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/productList.fxml"));
            root = loader.load();

            //setting model
            ProductListController productListController = loader.getController();
            productListController.init(previousProductListCategory, eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle(previousProductListCategory + "View");
            stage.setScene(new Scene(root));
            //stage.show();
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }

    /**
     * Logs out the user
     * Used by logoutButton
     * @param actionEvent button click
     */
    @FXML
    private void logout(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/login.fxml"));
            root = loader.load();

            //setting model
            LoginController loginController = loader.getController();
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
            loginController.init(eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("LoginView");
            stage.setScene(new Scene(root));
            //stage.show();
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }

    /**
     * Removes selected product from the basket, adding it to product list
     * Used by removeFromBasket button
     * @param actionEvent button click
     */
    @FXML
    private void removeFromBasket(ActionEvent actionEvent){
        Product selected = basketTableView.getSelectionModel().getSelectedItem();
        if(selected!= null){

            try{
                //increasing amount in category product list
                eshopping.findProduct(selected.getName(), eshopping.getCategoryList(selected.getCategory())).setAmount
                        (eshopping.findProduct(selected.getName(),
                                eshopping.getCategoryList(selected.getCategory())).getAmount() + 1);
            } catch (MyException myException) {
                alertManagement.showAlert(myException);
                myException.printStackTrace();
            }

            //if 1 product left
            if(selected.getAmount() == 1){
                //removing from client basket
                Integer productIndex = eshopping.findProductIndex(eshopping.getClientMap().get(eshopping.returnLoggedUser())
                .getBasket().getProductList(), selected.getName());
                eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList()
                        .remove((int)productIndex);
            }
            else{
                //decrease amount of product in client's basket
                try{
                    eshopping.findProduct(selected.getName(),
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList()).setAmount
                            (eshopping.findProduct(selected.getName(),
                                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList())
                                    .getAmount() - 1);
                } catch (MyException myException) {
                    alertManagement.showAlert(myException);
                    myException.printStackTrace();
                }

            }
            basketTableView.getItems().setAll
                    (eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList());
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().calculateTotalCost();
            totalCostLabel.setText("Total cost: " + eshopping.getClientMap().get
                    (eshopping.returnLoggedUser()).getBasket().getTotalCost().toString());

        }
    }

    /**
     * pays for all of the products, clears the basket, updates the user's money
     * Used by payButton
     * @param actionEvent button click
     */
    @FXML
    private void pay(ActionEvent actionEvent){
        //if(!basketTableView.getItems().isEmpty())
        if(!basketTableView.getItems().isEmpty() && eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney() >=
                eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getTotalCost()){

            //update client's money
            Double clientMoney =
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney();
            Double totalCost =
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getTotalCost();
            Double newMoney = clientMoney-totalCost;
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).setMoney(newMoney);
            plnLabel.setText("PLN: "
                    + eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney().toString());

            //clear client's basket
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList().clear();

            //update total cost
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().calculateTotalCost();
            totalCostLabel.setText("Total cost: " + eshopping.getClientMap().get
                    (eshopping.returnLoggedUser()).getBasket().getTotalCost().toString());

            basketTableView.getItems().setAll
                    (eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList());

            alertManagement.pay();
        }
        else{
            try {
                throw new MyException("No products in the basket");
            } catch (MyException myException) {
                alertManagement.showAlert(myException);
            }
        }
    }
}
