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
 * Class of controller of product list
 * @author Jaroslaw Palasz
 * @version 1.0
 */
public class ProductListController {

    /**
     * Model object containing all of the data
     */
    private Eshopping eshopping;

    /**
     * Manager of alerts
     */
    private AlertManagement alertManagement;

    /**
     * Name of previously chosen category
     */
    private String selectedCategory;

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
     * Button adding product to basket
     */
    @FXML
    private Button addToBasketButton;
    /**
     * Basket button
     */
    @FXML
    private Button basketButton;

    /**
     * Table representing list of products from chosen category
     */
    @FXML
    private TableView<Product> productCategoryTableView;
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
     * Label showing who is currently logged in
     */
    @FXML
    private Label loggedLabel;
    /**
     * Label showing chosen category of products
     */
    @FXML
    private Label categoryLabel;

    /**
     * Getter of name of previously chosen category
     * @return name of previously chosen category
     */
    public String getSelectedCategory() { return selectedCategory; }

    /**
     * Setter of name of previously chosen category
     * @param selectedCategory name of previously chosen category
     */
    public void setSelectedCategory(String selectedCategory) { this.selectedCategory = selectedCategory; }

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
     * Initializes the controller, sets the name of selected category
     * sets the model, product list table and all of the labels
     * @param categoryList name of previously chosen category
     * @param eshopping eshopping object
     */
    public void init(String categoryList, Eshopping eshopping) {
        alertManagement = new AlertManagement();
        setEshopping(eshopping);
        setSelectedCategory(categoryList);
        loggedLabel.setText(eshopping.returnLoggedUser());
        categoryLabel.setText(selectedCategory);

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
        productCategoryTableView.getItems().setAll(eshopping.getCategoryList(categoryList));

        //productCategoryTableView.addFocusListener(this);
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
                    ("pl/polsl/lab/view/category.fxml"));
            root = loader.load();

            //setting model
            CategoryController categoryController = loader.getController();
            categoryController.init(eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("CategoryView");
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
     * Adds product to basket and refreshes the table
     * @param actionEvent button click
     */
    @FXML
    private void addToBasket(ActionEvent actionEvent){
        //TODO: try-catch to dialog box
        Product selected = productCategoryTableView.getSelectionModel().getSelectedItem();
        if(selected!=null && selected.getAmount()>0){
            try{
                eshopping.addToBasket(eshopping.returnLoggedUser(), selected);
            } catch (MyException myException) {
                alertManagement.showAlert(myException);
                myException.printStackTrace();
            }
            try{
                eshopping.findProduct(selected.getName(),
                        eshopping.getCategoryList(getSelectedCategory())).setAmount(selected.getAmount()-1);
            } catch (MyException myException) {
                alertManagement.showAlert(myException);
                myException.printStackTrace();
            }
            productCategoryTableView.getItems().setAll(eshopping.getCategoryList(getSelectedCategory()));
        }
    }

    /**
     * Goes to the user's basket
     * @param actionEvent button click
     */
    @FXML
    private void basket(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/basket.fxml"));
            root = loader.load();

            //setting model
            BasketController basketController = loader.getController();
            basketController.init(eshopping, selectedCategory);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("BasketView");
            stage.setScene(new Scene(root));

            //loader.getController()->set model
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }
}
