package Model.Shop;

import java.util.HashMap;

public class Shop {
    private final HashMap<Integer,Integer> shop=new HashMap<>();
    private final String shopName,shopAddress,contactNo;
    public Shop(String shopName, String shopAddress,String contactNo){
        this.shopName=shopName;
        this.shopAddress=shopAddress;
        this.contactNo=contactNo;
    }
    public String getShopName(){
        return shopName;
    }
    public String getShopAddress(){
        return shopAddress;
    }
    public String getContactNo(){
        return contactNo;
    }
    public void addToShop(int productId,int quantity){
        this.shop.put(productId,quantity);
    }
    public HashMap<Integer,Integer> getProductsFromShop(){
        return this.shop;
    }
}
