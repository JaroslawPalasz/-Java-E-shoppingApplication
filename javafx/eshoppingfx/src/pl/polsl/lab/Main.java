package pl.polsl.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.polsl.lab.controller.LoginController;
import pl.polsl.lab.model.Eshopping;

/**
 * Main class of eshopping application
 * @author Jaroslaw Palasz
 * @version 2.0 with JavaFX
 */
public class Main extends Application {

    /**
     * Start of application
     * @param primaryStage primary stage of GUI
     * @throws Exception when error encountered
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Eshopping eshopping = new Eshopping();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        Parent root = loader.load();

        //setting model
        LoginController loginController = loader.getController();
        loginController.init(eshopping);
        //Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("LoginView");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Main method of the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
