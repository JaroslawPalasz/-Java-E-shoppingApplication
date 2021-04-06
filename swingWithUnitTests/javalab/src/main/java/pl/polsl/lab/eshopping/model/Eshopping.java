package pl.polsl.lab.eshopping.model;

import pl.polsl.lab.eshopping.MyException;

import java.security.KeyPair;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Class holding all of the model of application
 * clients, baskets, and products
 */
public class Eshopping {
    /**
     * ClientMap contains all of the client objects
     */
    private Map<String , Client> clientMap;

    /**
     * category1list represents list of products in category 1
     */
    private List<Product> category1List;
    /**
     * category2List represents list of products in category 2
     */
    private List<Product> category2List;
    /**
     * category3List represents list of products in category 3
     */
    private List<Product> category3List;
    /**
     * category4List represents list of products in category 4
     */
    private List<Product> category4List;
    /**
     * categoryLists represents all of lists of products of different categories, in one list
     */
    private List<List<Product>> categoryLists;

    /**
     * Helper function which flattens list of lists of type T into one list of type T
     * @param listList nested list of lists of type T
     * @param <T> type T
     * @return flattened list of type T
     */
    public <T> List<T> flattenListofLists(List<List<T>> listList){
        List<T> flattenedList = new ArrayList<>();
        listList.forEach(flattenedList::addAll);
        return flattenedList;
    }

    /**
     * Searches for products which minimum price is that given in parameter
     * @param price Double price by which funtion does it searching
     * @return ArrayList of products with price more or equal to that provided in parameter
     * @throws MyException throws exception if price is incorrect (NaN or negative value)
     */
    public ArrayList<Product> findProductsWithPriceAtLeast(Double price) throws MyException{
        if(price.isNaN() || price < 0.0)
            throw new MyException("Price is incorrect");
        List<Product> flattenedList = flattenListofLists(this.categoryLists);
        return (ArrayList<Product>)
                flattenedList.stream().filter(product -> product.getPrice() >= price)
                        .collect(Collectors.toList());
    }

    /**
     * Searches for given product in a product list
     * @param productName String name of the product
     * @param productList List of products
     * @return if found in list, product object, else throws exception
     * @throws MyException if list is empty or product hasn't been found
     */
    public Product findProduct(String productName, List<Product> productList) throws MyException {
        if(productList!=null){
            return productList.stream().filter(product -> product.getName().contains(productName))
                    .findFirst()
                    .orElseThrow(()-> new MyException("No product found"));
        }
        else
            throw new MyException("Empty list");
    }

    /**
     * Getter of list of products of chosen category
     * @param categoryList name of category
     * @return list of products in given category
     */
    public List<Product> getCategoryList(String categoryList) {
        switch (categoryList){
            case("Category1"):
                return  category1List;
            case("Category2"):
                return category2List;
            case("Category3"):
                return category3List;
            case("Category4"):
                return category4List;
            default:
                break;
        }
        return null;
    }

    /**
     * Getter of list of products from category 1
     * @return product list from category1
     */
    public List<Product> getCategory1List() { return category1List; }

    /**
     * Getter of list of products from category 2
     * @return product list from category2
     */
    public List<Product> getCategory2List() { return category2List; }

    /**
     * Getter of list of products from category 3
     * @return product list from category3
     */
    public List<Product> getCategory3List() { return category3List; }

    /**
     * Getter of list of products from category 4
     * @return product list from category4
     */
    public List<Product> getCategory4List() { return category4List; }

    /**
     * Getter of map of clients
     * @return map of clients
     */
    public Map<String, Client> getClientMap() { return clientMap; }

    /**
     * Getter of certain client object contained in a map
     * @param username username of client
     * @return client from client map
     */
    public Client getClient(String username){
        return clientMap.get(username);
    }

    /**
     * Constructor of Eshopping class
     * creates two client objects
     * creates list of products in 4 categories
     * @throws MyException if Product or Client have invalid input provided
     */
    public Eshopping() throws MyException {
        //creating 2 testing clients:
        clientMap = new HashMap<>();
        Basket bask = new Basket();
        Client client1 = new Client("client1", "123", new Basket(), 200.0);
        Client client2 = new Client("client2", "123", new Basket(), 100.0);
        clientMap.put(client1.getUsername(), client1);
        clientMap.put(client2.getUsername(), client2);

        //creating test product list for category1
        category1List = new ArrayList<>();
        category2List = new ArrayList<>();
        category3List = new ArrayList<>();
        category4List = new ArrayList<>();

        category1List.add(new Product("Category1", "c1product1", 1.0, 3, "description of product1"));
        category1List.add(new Product("Category1", "c1product2", 2.0, 3, "description of product2"));
        category1List.add(new Product("Category1", "c1product3", 3.0, 3, "description of product3"));
        category1List.add(new Product("Category1", "c1product4", 4.0, 5, "description of product4"));
        category1List.add(new Product("Category1", "c1product5", 5.0, 3, "description of product5"));
        category1List.add(new Product("Category1", "c1product6", 6.0, 3, "description of product6"));
        category1List.add(new Product("Category1", "c1product7", 7.0, 0, "description of product7"));
        category1List.add(new Product("Category1", "c1product8", 8.0, 3, "description of product8"));

        category1List.add(new Product("Category1", "c1product9", 1.0, 3, "description of product9"));
        category1List.add(new Product("Category1", "c1product10", 1.0, 3, "description of product10"));
        category1List.add(new Product("Category1", "c1product11", 1.0, 3, "description of product11"));
        category1List.add(new Product("Category1", "c1product12", 1.0, 3, "description of product12"));
        category1List.add(new Product("Category1", "c1product13", 2.0, 3, "description of product13"));
        category1List.add(new Product("Category1", "c1product14", 1.0, 3, "description of product14"));
        category1List.add(new Product("Category1", "c1product15", 3.2, 3, "description of product15"));
        category1List.add(new Product("Category1", "c1product16", 4.3, 3, "description of product16"));
        category1List.add(new Product("Category1", "c1product17", 1.0, 3, "description of product17"));
        category1List.add(new Product("Category1", "c1product18", 1.0, 3, "description of product18"));

        category2List.add(new Product("Category2", "c2product1", 1.6, 3, "description of product1"));

        category3List.add(new Product("Category3", "c3product1", 1.0, 8, "description of product1"));

        category4List.add(new Product("Category4", "c4product1", 40.0, 1, "description of product1"));

        categoryLists = Arrays.asList(category1List, category2List, category3List, category4List);
    }

    /**
     * adds product to client's basket
     * creates new product and adds it to the basket if the basket doesn't contain given product
     * else sets product's amount higher
     * else throws exception if there is no such client
     * @param client client which gets the given product added to the basket
     * @param product product which is added to client's basket
     * @throws MyException throws exception if client doesn't exist
     */
    public void addToBasket(String client, Product product) throws MyException {
        if(clientMap.get(client)==null){
            throw new MyException("There is no such client");
        }
        else if(product.getName()==null){
            throw new MyException("There is no such product");
        }
        else{
            int found = 0;
            for(int i=0;i<clientMap.get(client).getBasket().getProductList().size();i++){
                if(clientMap.get(client).getBasket().getProductList().get(i).getName().equals(product.getName())){
                    found = 1;
                    clientMap.get(client).getBasket().getProductList().get(i).setAmount(
                            clientMap.get(client).getBasket().getProductList().get(i).getAmount()+1);
                }
            }
            if(found==0){
                Product newProduct = new Product(product.getCategory(), product.getName(), product.getPrice(),
                        1, product.getDescription());
                clientMap.get(client).getBasket().getProductList().add(newProduct);
            }
        }
    }

    /**
     * Finds index of given product in list of products and returns it
     * @param productList list of products to be searched
     * @param productName name of product
     * @return product index or null if none was found
     */
    public Integer findProductIndex(List<Product> productList, String productName){
        if(productList!=null){
            for(int i=0;i<productList.size();i++){
                if(productList.get(i).getName().equals(productName)){
                    return productList.indexOf(productList.get(i));
                }
            }
        }
        return null;
    }

    /**
     * adds names of products from given list of products to ArrayList of strings and returns the latter
     * helper function
     * @param productList list of products
     * @param productNameList list of strings to be updated with names of products
     * @return ArrayList of product names or null if initial productNameList not empty or productList is null
     */
    public ArrayList<String> convertProductToStringList(ArrayList<Product> productList,
                                                        ArrayList<String> productNameList){
        if(productList!=null && productNameList.isEmpty() && productList.size() > 0){
            for (Product product : productList) {
                productNameList.add(product.getName());
            }
            return(productNameList);
        }
        return null;
    }

    /**
     * finds the key (username) of logged client
     * @return username of client of null if nobody is logged
     */
    public String returnLoggedUser(){
        for(Map.Entry<String, Client> entry : clientMap.entrySet()) {
            if (entry.getValue().getLogged()){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Checks if credentials given by user are correct and returns key (username) of client if credentials match
     * else throws exception
     * @param login username inputted by user
     * @param password password inputted by user
     * @return key (username) of client
     * @throws MyException throws exception if there is no such client
     */
    public String checkAndReturnLogin(String login, String password) throws MyException{
        Client checkedClient = new Client(login, password, null, 0.0);
        for(Map.Entry<String, Client> entry : clientMap.entrySet()){
            if(entry.getValue().getUsername().equals(checkedClient.getUsername()) &&
                    entry.getValue().getPassword().equals(checkedClient.getPassword())){
                return entry.getKey();
            }
        }
        throw new MyException("Invalid login/password");
        //return null;
    }
}
