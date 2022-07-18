package FileOperations;

import Model.ShoppingCart.ShoppingCart;

import java.util.HashMap;

public interface ShoppingCartFileHandlerService {
    void writeToCart(String userName, HashMap<Integer,Integer> shoppingCart);
    boolean containsShoppingCart(String userName);
    ShoppingCart getShoppingCart(String userName);
    void removeFromCart(String userName);
    void updateCartOwnerName(String userName);
}
