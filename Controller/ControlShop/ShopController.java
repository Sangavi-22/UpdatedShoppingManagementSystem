package Controller.ControlShop;

import DataStorage.*;
import Model.Shop.Shop;
import Model.MobilePhone.*;
import View.Shop.*;
import Controller.ControlMobilePhone.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopController implements ShopControllerService {
    private PhoneControllerService phoneController;
    private Shop shop;
    private final ShopViewService shopView=new ShopView();
    private final PrintShopInfoViewService printShopInfoView=new PrintShopInfoView();
    private final DataSourceService dataSource=DataSource.getInstance();
    public void checkShop(){
        if(shop==null) {
            shop=new Shop("Mobile Galaxy","No.10 Ramaswamy Street, Chennai-12","044-278920231");
        }
    }
    public void addToShop(int productId, int quantity){
        checkShop();
        shop.addToShop(productId,quantity);
    }
    public HashMap<Integer, Integer> getProductsFromShop(){
        checkShop();
        return shop.getProductsFromShop();
    }
    public void displayPhoneBasicInfo(int phoneId){
        phoneController=new PhoneController();
        phoneController.getPhone(phoneId);
        phoneController.printBasicInfo();
        phoneController.printQuantity(dataSource.getPhones().get(phoneId));
    }
    public boolean listProductsInShop(){
        boolean displayed=false;
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        if(!(phones.size()==0)){
            for(int phoneId:phones.keySet()) {
                if(!(phones.get(phoneId)==0)) {
                    displayPhoneBasicInfo(phoneId);
                    displayed=true;
                }
            }
        }
        return displayed;
    }
    public void displayParticularProduct(int phoneId){
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        if(!(phones.get(phoneId)==0)){
            phoneController=new PhoneController();
            phoneController.getPhone(phoneId);
            phoneController.updateView();
            phoneController.printQuantity(phones.get(phoneId));
        }
    }
    public int addProductToShop(){
        phoneController=new PhoneController();
        phoneController.setInputs();
        int quantity=phoneController.inputQuantityOfPhoneToAdd();
        HashMap<Integer,PhoneSpecifications>phoneDetails=dataSource.getPhoneDetails();
        boolean add=shopView.confirmAddProduct();
        if(quantity==0 || (!add)) {
            return 3;
        }
        for (int phoneId : phoneDetails.keySet()) {
            if(phoneDetails.get(phoneId).getModelName().equalsIgnoreCase(phoneController.getModelName()) && phoneDetails.get(phoneId).getStorageCapacity().equalsIgnoreCase(phoneController.getStorageCapacity())) {
                dataSource.updatePhoneQuantity(phoneId,quantity);
                phoneController.setPhoneId(phoneId);
                phoneController.updateView();
                phoneController.printQuantity(dataSource.getPhones().get(phoneId));
                this.checkStockNillList(phoneController.getPhoneId(),quantity);
                return 2;
            }
        }
        addToShop(phoneController.getPhoneId(), quantity);
        dataSource.addPhoneToShop(phoneController.getPhoneId(), getProductsFromShop().get(phoneController.getPhoneId()));
        phoneController.addPhoneToDataSource();
        phoneController.updateView();
        phoneController.printQuantity(dataSource.getPhones().get(phoneController.getPhoneId()));
        checkStockNillList(phoneController.getPhoneId(),quantity);
        return 1;
    }
    public boolean searchProduct(String inputToSearch) {
        boolean displayedSearchResult=false;
        ArrayList<Integer> searchResult = dataSource.searchPhoneFromFile(inputToSearch);
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        for(int phoneId:phones.keySet()){
            if(searchResult.contains(phoneId) && phones.get(phoneId)!=0){
                displayPhoneBasicInfo(phoneId);
                displayedSearchResult=true;
            }
        }
        return displayedSearchResult;
    }
    public boolean containsProduct(int id){
        return dataSource.getPhones().containsKey(id);
    }
    public boolean removeProductWithItsId(int phoneId){
        boolean removed=false;
        if(dataSource.containsPhone(phoneId)) {
            displayPhoneBasicInfo(phoneId);
            if(shopView.confirmDeleteProduct()) {
                dataSource.removePhone(phoneId);
                removed=true;
            }
        }
        return removed;
    }
    public boolean removeProductWithName(String modelName){
        return searchProduct(modelName);
    }
    public boolean checkStock() {
        boolean flag = false;
        ArrayList<Integer> productIds = dataSource.readStockNilProducts();
        if(!(productIds.size()==0)) {
            for (Integer productId : productIds) {
                if(!(dataSource.getPhones().get(productId) > 0)){
                    displayPhoneBasicInfo(productId);
                    flag = true;
                }
            }
        }
        return flag;
    }
    public boolean updateStock(int productId,int count) {
        dataSource.updatePhoneQuantity(productId,count);
        if(count>0) {
            dataSource.removeFromStockNilList(productId);
        }
        return true;
    }
    public boolean phoneAvailable(int id,int orderedQuantity){
        HashMap<Integer,Integer>products=dataSource.getPhones();
        if(products.get(id)==0 && !(dataSource.readStockNilProducts().contains(id))){
            dataSource.addToStockNillList(id);
            return false;
        }
        else return products.get(id) >= orderedQuantity;
    }
    public void checkStockNillList(int productId,int quantity){
        if(dataSource.readStockNilProducts().contains(productId) && quantity>=1) {
            dataSource.removeFromStockNilList(productId);
        }
        else if(quantity==0 && !(dataSource.readStockNilProducts().contains(productId))) {
            dataSource.addToStockNillList(productId);
        }
    }
    public void updateView(){
        checkShop();
        printShopInfoView.printShopDetails(shop.getShopName(),shop.getShopAddress(),shop.getContactNo());
    }
}
