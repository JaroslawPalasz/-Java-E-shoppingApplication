package pl.polsl.lab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.polsl.lab.model.Eshopping;
import pl.polsl.lab.view.AlertManagement;

import java.io.IOException;
import java.util.Objects;

/**
 * Class of controller of category view
 * @author Jaroslaw Palasz
 * @version 1.0
 */
public class CategoryController {

    /**
     * Model object containing all of the data
     */
    private Eshopping eshopping;

    /**
     * Manager of alerts
     */
    private AlertManagement alertManagement;

    /**
     * Back button
     */
    @FXML
    private Button backButton;
    /**
     * Logout button
     */
    @FXML
    private Button logoutButton;
    /**
     * Category 1 button
     */
    @FXML
    private Button category1Button;
    /**
     * Category 2 button
     */
    @FXML
    private Button category2Button;
    /**
     * Category 3 button
     */
    @FXML
    private Button category3Button;
    /**
     * Category 4 button
     */
    @FXML
    private Button category4Button;

    /**
     * Label showing who is currently logged in
     */
    @FXML
    private Label loggedLabel;

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
     * Initializes the controller
     * sets the model and loggedLabel
     * @param eshopping eshopping object
     */
    public void init(Eshopping eshopping){
        alertManagement = new AlertManagement();
        setEshopping(eshopping);
        loggedLabel.setText(eshopping.returnLoggedUser());
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
                    ("pl/polsl/lab/view/login.fxml"));
            root = loader.load();

            //setting model
            LoginController loginController = loader.getController();
            eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
            loginController.init(eshopping);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setTitle("LoginView");
            stage.setScene(new Scene(root));
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
     * Goes to the product list from category 1
     * @param actionEvent button click
     */
    @FXML
    private void chooseCategory1(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/productList.fxml"));
            root = loader.load();

            //setting model
            ProductListController productListController = loader.getController();
            productListController.init("Category1", eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("Category1View");
            stage.setScene(new Scene(root));

            //loader.getController()->set model
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }

    /**
     * Goes to the product list from category 2
     * @param actionEvent button click
     */
    @FXML
    private void chooseCategory2(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/productList.fxml"));
            root = loader.load();

            //setting model
            ProductListController productListController = loader.getController();
            productListController.init("Category2", eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("Category2View");
            stage.setScene(new Scene(root));

            //loader.getController()->set model
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }

    /**
     * Goes to the product list from category 3
     * @param actionEvent button click
     */
    @FXML
    private void chooseCategory3(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/productList.fxml"));
            root = loader.load();

            //setting model
            ProductListController productListController = loader.getController();
            productListController.init("Category3", eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("Category3View");
            stage.setScene(new Scene(root));

            //loader.getController()->set model
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }

    /**
     * Goes to the product list from category 4
     * @param actionEvent button click
     */
    @FXML
    private void chooseCategory4(ActionEvent actionEvent){
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/productList.fxml"));
            root = loader.load();

            //setting model
            ProductListController productListController = loader.getController();
            productListController.init("Category4", eshopping);

            Stage stage = (Stage) backButton.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("Category4View");
            stage.setScene(new Scene(root));

            //loader.getController()->set model
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            exception.printStackTrace();
        }
    }
}
