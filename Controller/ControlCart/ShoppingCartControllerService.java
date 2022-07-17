package Controller.ControlCart;

import Model.ShoppingCart.ShoppingCart;
import java.util.HashMap;

public interface ShoppingCartControllerService {
    ShoppingCart getShoppingCart(String userName);
    void addProductsToCart(String userName,int productId, int orderedQuantity);
    HashMap<Integer,Integer> getProductsFromCart();
    boolean productInCartAlready(String userName, int id);
    void removeProductFromCart(String userName, int id);
    void updateProductQuantityInCart(String userName, int id, int count);
    boolean displayCart(String userName);
    void checkShoppingCart(String userName);
    void writeProductsFromCartToDataSource(String userName);
}
