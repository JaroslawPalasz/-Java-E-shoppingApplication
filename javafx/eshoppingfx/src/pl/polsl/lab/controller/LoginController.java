package pl.polsl.lab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.polsl.lab.MyException;
import pl.polsl.lab.model.Eshopping;
import pl.polsl.lab.view.AlertManagement;

import java.io.IOException;
import java.util.Objects;

/**
 * Class of controller of login view
 * @author Jaroslaw Palasz
 * @version 1.0
 */
public class LoginController {

    /**
     * Model object containing all of the data
     */
    private Eshopping eshopping;

    /**
     * Manager of alerts
     */
    private AlertManagement alertManagement;

    /**
     * Login button
     */
    @FXML
    private Button loginButton;

    /**
     * Text field to enter username
     */
    @FXML
    private TextField usernameField;
    /**
     * Password field to enter password
     */
    @FXML
    private PasswordField passwordField;

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
     * sets the model
     * @param eshopping eshopping object
     */
    public void init(Eshopping eshopping){
        alertManagement = new AlertManagement();
        setEshopping(eshopping);
    }

    /**
     * Checks username and password input and, if they are correct, logs in the user
     * @param actionEvent button click
     */
    @FXML
    private void login(ActionEvent actionEvent){
        Parent root;
        try{
            String username = usernameField.getText();
            String password = passwordField.getText();
            String key = null;
            try{
                key = eshopping.checkAndReturnLogin(username, password);
            } catch (MyException myException) {
                alertManagement.showAlert(myException);
                System.out.println("Exception: " + myException);
                //myException.printStackTrace();
            }
            if(key!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource
                        ("pl/polsl/lab/view/category.fxml"));
                root = loader.load();

                //setting model
                CategoryController categoryController = loader.getController();
                eshopping.getClientMap().get(key).setLogged(true);
                categoryController.init(eshopping);
                //categoryController.setEshopping(eshopping);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setTitle("CategoryView");
                stage.setScene(new Scene(root));
            }
            /*root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource
                    ("pl/polsl/lab/view/category.fxml")));*/
            //Stage stage = new Stage();
            //stage.show();
        }
        catch (IOException exception){
            alertManagement.showAlert(exception);
            //exception.printStackTrace();
        }
    }
}
