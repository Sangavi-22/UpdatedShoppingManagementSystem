package Controller.ControlCart;

import DataStorage.*;
import Model.ShoppingCart.*;
import Controller.ControlMobilePhone.*;
import java.util.HashMap;

public class ShoppingCartController implements ShoppingCartControllerService{
    private ShoppingCart model;
    private final DataSourceService dataSource= DataSource.getInstance();
    public ShoppingCartController(ShoppingCart model){
        this.model=model;
    }
    public ShoppingCart getShoppingCart(String userName){
        checkShoppingCart(userName);
        return model;
    }
    public void addProductsToCart(String userName,int productId,int orderedQuantity){
        checkShoppingCart(userName);
        model.addProductsToCart(productId,orderedQuantity);
    }
    public HashMap<Integer,Integer> getProductsFromCart(){
        return model.getProductsFromCart();
    }
    public boolean productInCartAlready(String userName,int productId){
        checkShoppingCart(userName);
        return getProductsFromCart().containsKey(productId) && getProductsFromCart().get(productId)!=0;
    }
    public void removeProductFromCart(String userName,int productId){
        addProductsToCart(userName,productId,0);
    }
    public void updateProductQuantityInCart(String userName,int productId,int quantity){
        addProductsToCart(userName,productId,quantity);
    }
    public boolean displayCart(String userName){
        boolean flag=false;
        checkShoppingCart(userName);
        HashMap<Integer,Integer>productsInCart=getProductsFromCart();
        for(int productIds: productsInCart.keySet()) {
            if(productsInCart.get(productIds)!=0) {
                PhoneControllerService phoneController=new PhoneController();
                phoneController.getPhone(productIds);
                phoneController.printBasicInfo();
                phoneController.printOrderedQuantity(productsInCart.get(productIds));
                flag=true;
            }
        }
        return flag;
    }
    public void checkShoppingCart(String userName){
        if(getProductsFromCart().size()==0 && dataSource.containsCart(userName)){
            model = dataSource.getCart(userName);
        }
    }
    public void writeProductsFromCartToDataSource(String userName){
        dataSource.writeToCartFile(userName,getProductsFromCart());
    }
}
