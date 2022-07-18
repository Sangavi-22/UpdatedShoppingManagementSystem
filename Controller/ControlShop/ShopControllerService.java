package Controller.ControlShop;

import java.util.HashMap;

public interface ShopControllerService {
    void checkShop();
    void addToShop(int productId, int quantity);
    HashMap<Integer, Integer> getProductsFromShop();
    boolean listProductsInShop();
    void displayParticularProduct(int id);
    int addProductToShop();
    boolean searchProduct(String inputValue);
    boolean containsProduct(int id);
    boolean removeProductWithItsId(int id);
    boolean removeProductWithName(String modelName);
    boolean checkStock();
    boolean updateStock(int productId,int count);
    boolean phoneAvailable(int id, int quantity);
    void updateView();
}
