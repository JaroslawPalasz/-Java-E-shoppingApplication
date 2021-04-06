package pl.polsl.lab.eshopping.controller;

import pl.polsl.lab.eshopping.MyException;
import pl.polsl.lab.eshopping.model.Client;
import pl.polsl.lab.eshopping.model.Eshopping;
import pl.polsl.lab.eshopping.model.Product;
import pl.polsl.lab.eshopping.view.CategoryView;
import pl.polsl.lab.eshopping.view.EshoppingView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class of controller
 */
public class EshoppingController {
    /**
     * model
     */
    private final Eshopping eshopping;
    /**
     * view
     */
    private final EshoppingView eshoppingView;

    /**
     * Constructor of controller
     * @param eshopping represents model of application
     * @param eshoppingView represents view, gui of application
     */
    public EshoppingController(Eshopping eshopping, EshoppingView eshoppingView) {
        this.eshopping = eshopping;
        this.eshoppingView = eshoppingView;
        multilistener();
    }

    /**
     * Listener of user input
     */
    public void multilistener() {
        eshoppingView.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Login")) {
                    String password = new String(eshoppingView.getPasswordField1().getPassword());
                    String key = null;
                    try {
                        key = eshopping.checkAndReturnLogin(eshoppingView.getTextField1().getText(), password);
                    } catch (MyException myException) {
                        System.out.println("Exception: " + myException);
                    }
                    if(key!= null){
                        System.out.println("Login");
                        eshopping.getClientMap().get(key).setLogged(true);
                        eshoppingView.categoryView(eshopping.getClientMap().get(key).getUsername());
                    }
                }
            }
        });

        eshoppingView.getCategoryView().getCategory1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Category1")) {
                    System.out.println("Category1");

                    ArrayList<String> productNameList = new ArrayList<>();
                    productNameList = eshopping.convertProductToStringList
                            ((ArrayList<Product>) eshopping.getCategory1List(), productNameList);
                    /*for (Product product : eshopping.getCategory1List()) {
                        productNameList.add(product.getName());
                    }*/
                    eshoppingView.productListView(productNameList,
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney(),
                            eshopping.returnLoggedUser(),
                            "Category1");
                }
            }
        });
        eshoppingView.getCategoryView().getCategory2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Category2")) {
                    System.out.println("Category2");

                    ArrayList<String> productNameList = new ArrayList<>();
                    productNameList = eshopping.convertProductToStringList
                            ((ArrayList<Product>) eshopping.getCategory2List(), productNameList);
                    /*for (Product product : eshopping.getCategory1List()) {
                        productNameList.add(product.getName());
                    }*/
                    eshoppingView.productListView(productNameList,
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney(),
                            eshopping.returnLoggedUser(),
                            "Category2");
                }
            }
        });
        eshoppingView.getCategoryView().getCategory3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Category3")) {
                    System.out.println("Category3");

                    ArrayList<String> productNameList = new ArrayList<>();
                    productNameList = eshopping.convertProductToStringList
                            ((ArrayList<Product>) eshopping.getCategory3List(), productNameList);
                    /*for (Product product : eshopping.getCategory1List()) {
                        productNameList.add(product.getName());
                    }*/
                    eshoppingView.productListView(productNameList,
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney(),
                            eshopping.returnLoggedUser(),
                            "Category3");
                }
            }
        });
        eshoppingView.getCategoryView().getCategory4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Category4")) {
                    System.out.println("Category4");

                    ArrayList<String> productNameList = new ArrayList<>();
                    productNameList = eshopping.convertProductToStringList
                            ((ArrayList<Product>) eshopping.getCategory4List(), productNameList);
                    /*for (Product product : eshopping.getCategory1List()) {
                        productNameList.add(product.getName());
                    }*/
                    eshoppingView.productListView(productNameList,
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney(),
                            eshopping.returnLoggedUser(),
                            "Category4");
                }
            }
        });
        eshoppingView.getCategoryView().getLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Logout")) {
                    System.out.println("Logout");
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
                    eshoppingView.loginView();
                }
            }
        });
        eshoppingView.getCategoryView().getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Back")) {
                    System.out.println("Back");
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
                    eshoppingView.loginView();
                }
            }
        });

        eshoppingView.getProductListView().getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Logout")) {
                    System.out.println("Logout");
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
                    if(!eshoppingView.getProductListView().getProductList().isSelectionEmpty())
                        eshoppingView.getProductListView().getProductList().clearSelection();
                    eshoppingView.getProductListView().getPrice().setText("Price: ");
                    eshoppingView.loginView();
                }
            }
        });
        eshoppingView.getProductListView().getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("BackToCategoryView")) {
                    if(!eshoppingView.getProductListView().getProductList().isSelectionEmpty())
                        eshoppingView.getProductListView().getProductList().clearSelection();
                    eshoppingView.getProductListView().getPrice().setText("Price: ");
                    eshoppingView.getProductListView().getAmountLeft().setText("Left: ");
                    System.out.println("BackToCategoryView");
                    eshoppingView.categoryView(eshopping.returnLoggedUser());
                }
            }
        });
        eshoppingView.getProductListView().getProductList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String category = eshoppingView.getProductListView().getCategory().getText();
                System.out.println("Category is: " + category);

                Integer index = null;
                if(!eshoppingView.getProductListView().getProductList().isSelectionEmpty())
                    index = eshopping.findProductIndex(eshopping.getCategoryList(category),
                            eshoppingView.getProductListView().getProductList().getSelectedValue().toString());
                if(index!= null){
                    System.out.println("Index is not null,");
                    System.out.println("Index is: " + index);

                    String price = new String();
                    if(eshopping.getCategoryList(category).get(index).getAmount() == 0){
                        eshoppingView.getProductListView().getAddToBasket().setEnabled(false);
                    }
                    else {
                        eshoppingView.getProductListView().getAddToBasket().setEnabled(true);
                    }
                    eshoppingView.getProductListView().getAmountLeft().setText("Left: " +
                            eshopping.getCategoryList(category).get(index).getAmount().toString());
                    price = eshopping.getCategoryList(category).get(index).getPrice().toString();
                    eshoppingView.getProductListView().getPrice().setText("Price: " + price);
                }
            }
        });
        eshoppingView.getProductListView().getAddToBasket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = eshoppingView.getProductListView().getCategory().getText();
                Integer index = eshopping.findProductIndex(eshopping.getCategoryList(category),
                        eshoppingView.getProductListView().getProductList().getSelectedValue().toString());
                if(eshopping.getCategoryList(category).get(index).getAmount() > 0){
                    eshopping.getCategoryList(category).get(index).setAmount(
                            eshopping.getCategoryList(category).get(index).getAmount()-1);
                    try{
                        eshopping.addToBasket
                                (eshopping.returnLoggedUser(), eshopping.getCategoryList(category).get(index));
                    }catch (MyException exception){
                        System.out.println("Exception happened: " + exception);
                    }
                }
                if(eshopping.getCategoryList(category).get(index).getAmount() == 0){
                    eshoppingView.getProductListView().getAddToBasket().setEnabled(false);
                }
                eshoppingView.getProductListView().getAmountLeft().setText("Left: " +
                        eshopping.getCategoryList(category).get(index).getAmount().toString());

            }
        });
        eshoppingView.getProductListView().getBasket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Basket")) {
                    System.out.println("Basket");

                    ArrayList<String> productNameList = new ArrayList<>();
                    /*for (Product product : eshopping.getClient
                            (eshopping.returnLoggedUser()).getBasket().getProductList()) {
                        productNameList.add(product.getName());
                    }*/
                    productNameList = eshopping.convertProductToStringList((ArrayList<Product>) eshopping.getClient
                            (eshopping.returnLoggedUser()).getBasket().getProductList(), productNameList);

                    Double money = eshopping.getClient(eshopping.returnLoggedUser()).getMoney();
                    String loggedUser = eshopping.returnLoggedUser();

                    System.out.println("Money: " + money.toString());
                    System.out.println("LoggedUser: " + loggedUser);
                    //setting total cost
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().calculateTotalCost();

                    eshoppingView.basketView(productNameList, money, loggedUser,
                            eshoppingView.getProductListView().getCategory().getText(),
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getTotalCost());
                }
            }
        });

        eshoppingView.getBasketView().getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("BackToProductView")) {
                    if(!eshoppingView.getBasketView().getList1().isSelectionEmpty())
                        eshoppingView.getBasketView().getList1().clearSelection();
                    eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(false);
                    eshoppingView.getBasketView().getPrice().setText("Price: ");
                    eshoppingView.getBasketView().getAmount().setText("Left: ");
                    System.out.println("BackToProductListView");

                    //TODO: to function
                    ArrayList<String> productNameList = new ArrayList<>();
                    productNameList = eshopping.convertProductToStringList(
                            (ArrayList<Product>) eshopping.getCategoryList
                            (eshoppingView.getBasketView().getPreviousProductListCategory()), productNameList);

                    /*for (Product product : eshopping.getCategoryList
                            (eshoppingView.getBasketView().getPreviousProductListCategory())) {
                        productNameList.add(product.getName());
                    }*/
                    eshoppingView.productListView(productNameList,
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney(),
                            eshopping.returnLoggedUser(),
                            eshoppingView.getBasketView().getPreviousProductListCategory());
                }
            }
        });
        eshoppingView.getBasketView().getList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String category = eshoppingView.getBasketView().getPreviousProductListCategory();
                System.out.println("Category is: " + category);

                Integer index = null;
                if(!eshoppingView.getBasketView().getList1().isSelectionEmpty())
                    index = eshopping.findProductIndex(eshopping.getClientMap().get
                                    (eshopping.returnLoggedUser()).getBasket().getProductList(),
                            eshoppingView.getBasketView().getList1().getSelectedValue().toString());
                if(index!= null){
                    System.out.println("Index is not null,");
                    System.out.println("Index is: " + index);

                    //setting product amount, price and category labels
                    eshoppingView.getBasketView().getAmount().setText
                            ("Amount: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                    .getBasket().getProductList().get(index).getAmount());
                    eshoppingView.getBasketView().getPrice().setText
                            ("Price: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                            .getBasket().getProductList().get(index).getPrice());
                    eshoppingView.getBasketView().getProductCategory().setText
                            (eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                    .getBasket().getProductList().get(index).getCategory());
                    System.out.println("Category of selected product is: " +
                            eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                    .getBasket().getProductList().get(index).getCategory());


                    if(eshopping.getClientMap().get
                            (eshopping.returnLoggedUser()).getBasket().getProductList().get(index).getAmount() == 0)
                        eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(false);
                    else
                        eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(true);
                }
                System.out.println("Total cost: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                        .getBasket().getTotalCost().toString());
                eshoppingView.getBasketView().getTotalCost().setText
                        ("Total cost: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                .getBasket().getTotalCost().toString());
            }
        });
        eshoppingView.getBasketView().getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.equals("Logout")) {
                    System.out.println("Logout");
                    eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(false);
                    eshoppingView.loginView();
                }
            }
        });
        eshoppingView.getBasketView().getRemoveFromBasket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = eshoppingView.getBasketView().getList1().getSelectedIndex();
                boolean last = false;
                //save string for product
                String productName = eshoppingView.getBasketView().getList1().getSelectedValue().toString();
                //check amount
                Integer productIndex = eshopping.findProductIndex(eshopping.getClientMap().get
                                (eshopping.returnLoggedUser()).getBasket().getProductList(),
                        eshoppingView.getBasketView().getList1().getSelectedValue().toString());
                if(eshopping.getClientMap().get
                        (eshopping.returnLoggedUser()).getBasket().getProductList().get(productIndex).getAmount() == 1) {

                    //remove from list
                    System.out.println("Selected index: " + selectedIndex);
                    //eshoppingView.getBasketView().getList1().remove(selectedIndex);
                    //remove from client basket
                    eshopping.getClientMap().get
                            (eshopping.returnLoggedUser()).getBasket().getProductList().remove((int)productIndex);

                    //remove from list - set new list
                    //TODO: to function
                    ArrayList<String> productNameList = new ArrayList<>();
                    for (Product product : eshopping.getClient
                            (eshopping.returnLoggedUser()).getBasket().getProductList()) {
                        productNameList.add(product.getName());
                    }
                    eshoppingView.getBasketView().getList1().setListData(productNameList.toArray());
                    eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(false);
                    last = true;
                }
                else {
                    //decrease amount
                    eshopping.getClientMap().get(eshopping.returnLoggedUser())
                            .getBasket().getProductList().get(productIndex).setAmount
                            (eshopping.getClientMap().get
                                    (eshopping.returnLoggedUser()).getBasket()
                                    .getProductList().get(productIndex).getAmount() - 1);
                }
                //increase amount in product list
                Integer categoryProductIndex = eshopping.findProductIndex
                        (eshopping.getCategoryList(eshoppingView.getBasketView().getProductCategory().getText()),
                                productName);
                if(categoryProductIndex== null)
                    System.out.println("CategoryProductIndex is null");

                eshopping.getCategoryList(eshoppingView.getBasketView().getProductCategory().getText()).
                        get(categoryProductIndex).setAmount
                        (eshopping.getCategoryList(eshoppingView.getBasketView().getProductCategory().getText()).
                        get(categoryProductIndex).getAmount() + 1);

                eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().calculateTotalCost();
                eshoppingView.getBasketView().getTotalCost().setText("Total cost: " + eshopping.getClientMap().get
                        (eshopping.returnLoggedUser()).getBasket().getTotalCost());

                int size = eshoppingView.getBasketView().getList1().getModel().getSize();
                System.out.println("Size of list: " + size);
                //list is empty
                if(size == 0){
                    //disable remove from basket and pay buttons
                    eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(false);
                    eshoppingView.getBasketView().getPayButton().setEnabled(false);

                    //setting product amount, price and category labels, total cost
                    eshoppingView.getBasketView().getAmount().setText("Amount: ");
                    eshoppingView.getBasketView().getPrice().setText("Price: ");
                    eshoppingView.getBasketView().getProductCategory().setText("Category: ");
                }
                else
                {
                    //setting product amount, price and category labels, total cost
                    if(!last){
                        eshoppingView.getBasketView().getAmount().setText
                                ("Amount: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                        .getBasket().getProductList().get(productIndex).getAmount());
                        eshoppingView.getBasketView().getPrice().setText
                                ("Price: " + eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                        .getBasket().getProductList().get(productIndex).getPrice());
                        eshoppingView.getBasketView().getProductCategory().setText
                                (eshopping.getClientMap().get(eshopping.returnLoggedUser())
                                        .getBasket().getProductList().get(productIndex).getCategory());
                    }

                }
            }
        });
        eshoppingView.getBasketView().getPayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check total cost and pln
                //check if list is empty
                if(eshoppingView.getBasketView().getList1().getModel().getSize()!=0){
                    if(eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney() >=
                            eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getTotalCost()){

                        //clear client's basket
                        eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getProductList().clear();
                        //clear list
                        DefaultListModel defaultListModel = new DefaultListModel();
                        defaultListModel.clear();
                        eshoppingView.getBasketView().getList1().setModel(defaultListModel);

                        //update client's money and all buttons -> new function?
                        Double clientMoney = eshopping.getClientMap().get(eshopping.returnLoggedUser()).getMoney();
                        Double totalCost = eshopping.getClientMap().get(eshopping.returnLoggedUser()).getBasket().getTotalCost();
                        Double newMoney = clientMoney-totalCost;
                        eshopping.getClientMap().get(eshopping.returnLoggedUser()).setMoney(newMoney);

                        eshoppingView.getBasketView().getAmount().setText("Amount: ");
                        eshoppingView.getBasketView().getPrice().setText("Price: ");
                        eshoppingView.getBasketView().getProductCategory().setText("Category: ");
                        eshoppingView.getBasketView().getPln().setText(eshopping.getClientMap().get
                                (eshopping.returnLoggedUser()).getMoney().toString());

                        eshoppingView.getBasketView().getRemoveFromBasket().setEnabled(false);
                        eshoppingView.getBasketView().getPayButton().setEnabled(false);

                    }
                    else{
                        System.out.println("Not enough money!");
                    }
                }
            }
        });
    }
}
