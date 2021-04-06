package pl.polsl.lab.eshopping;

import pl.polsl.lab.eshopping.controller.EshoppingController;
import pl.polsl.lab.eshopping.model.Eshopping;
import pl.polsl.lab.eshopping.view.BasketView;
import pl.polsl.lab.eshopping.view.CategoryView;
import pl.polsl.lab.eshopping.view.EshoppingView;
import pl.polsl.lab.eshopping.view.ProductListView;

/**
 * Main Class
 */
public class Main {
    /**
     * Main constructor
     * @param args no parameters in command line
     */
    public static void main(String[] args) {
        Eshopping eshopping = null;
        try{
            eshopping = new Eshopping();
        } catch (MyException myException) {
            myException.printStackTrace();
        }

        CategoryView categoryView = new CategoryView();
        ProductListView productListView = new ProductListView();
        BasketView basketView = new BasketView();
        EshoppingView eshoppingView = new EshoppingView(categoryView, productListView, basketView);
        EshoppingController eshoppingController = new EshoppingController(eshopping, eshoppingView);
    }
}
