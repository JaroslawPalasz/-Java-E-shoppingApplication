package pl.polsl.lab.view;

import javafx.scene.control.Alert;
import pl.polsl.lab.MyException;

/**
 * Class handling alerts
 * @author Jaroslaw Palasz
 * @version 1.0
 */
public class AlertManagement {
    /**
     * Displays an exception error alert containing exception message
     * @param myException any exception
     */
    public void showAlert(/*MyException myException*/Exception myException){
        Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
        exceptionAlert.setTitle("Error");
        exceptionAlert.setHeaderText("Exception thrown by application");
        exceptionAlert.setContentText(myException.getMessage());

        exceptionAlert.show();
    }

    /**
     * Displays alert informing about successful payment
     */
    public void pay(){
        Alert paidAlert = new Alert(Alert.AlertType.INFORMATION);
        paidAlert.setTitle("Transaction successful");
        paidAlert.setHeaderText("You have successfully bought items from the basket");
        paidAlert.setContentText("");

        paidAlert.show();
    }
}
