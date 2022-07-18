package Controller.ControlOrder.OrderDetails;

import DataStorage.*;
import Controller.ControlOrder.CalculateDeliveryDateUtil.DeliveryDate;
import Model.Order.*;
import Model.MobilePhone.*;
import Model.ShoppingCart.*;
import View.Order.*;
import Controller.ControlCart.*;
import Controller.ControlShop.*;
import Controller.ControlOrder.AmountCalculationUtil.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderController implements OrderControllerService {
    private Order orderModel;
    private final DeliveryDate dates=new DeliveryDate();
    private final ShopControllerService shopController=new ShopController();
    private final DataSourceService dataSource= DataSource.getInstance();

    public int getOrderId(){
        return orderModel.getOrderId();
    }
    public String getCustomer(){
        return orderModel.getCustomer();
    }
    public HashMap<Integer,Integer> getOrderedProducts(){
        return orderModel.getOrderedProducts();
    }
    public LocalDate getDeliveryDate(){
        return orderModel.getDeliveryDate();
    }
    public String getDeliveryStatus(){
        return orderModel.getDeliveryStatus();
    }
    public int getBillAmount(){
        return orderModel.getBillAmount();
    }
    public void displayOrderDetails(int orderId,String userName,String deliveryStatus){
        dates.setDeliveryDate(orderId);
        orderModel = new Order(orderId, userName, dataSource.getProductsOfThatOrder(orderId).getProductsFromCart(), dates.getDeliveryDate(), deliveryStatus, dataSource.readFromBillFile(orderId));
        updateView();
    }
    public int addToOrders(ShoppingCart shoppingCartModel, String userName){
        int orderId,amount=0;
        boolean ordered=false;
        ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel);
        if(dataSource.getLastOrderNum()!=0) {
            orderId=dataSource.getLastOrderNum()+1;
        }
        else {
            orderId = 1;
        }
        HashMap<Integer,Integer>products=shoppingCartController.getProductsFromCart();
        CalculateBillAmountService billAmount=new CalculateBillAmount();
        for(int productId:products.keySet()) {
            if(products.get(productId)!=0) {
                dataSource.writeToOrdersList(userName, orderId, productId, products.get(productId),"not Delivered");
                dataSource.updatePhoneQuantity(productId,-products.get(productId));
                HashMap<Integer,Integer>phones=dataSource.getPhones();
                if(phones.get(productId)==0) {
                    dataSource.addToStockNillList(productId);
                }
                PhoneSpecifications phoneSpecifications = dataSource.getParticularPhone(productId);
                billAmount.calculateBillAmount(phoneSpecifications.getPrice(),products.get(productId));
                shoppingCartController.addProductsToCart(userName,productId,0);
                ordered=true;
            }
        }
        if(ordered) {
            if(dataSource.containsCart(userName)) {
                dataSource.freeCartForUser(userName);
            }
            dataSource.writeToBillFile(orderId,billAmount.getTotalCost());
            displayOrderDetails(orderId,userName,dataSource.readOrderStatus(userName).get(orderId));
            amount=billAmount.getTotalCost();
        }
        return amount;
    }
    public boolean removeOrder(int orderId, String userName){
        boolean removed=false;
        if(!(dataSource.readOrderStatus(userName).get(orderId).equals("Delivered"))) {
            ShoppingCart shoppingCart = dataSource.getProductsOfThatOrder(orderId);
            ShoppingCartControllerService shoppingCartController = new ShoppingCartController(shoppingCart);
            for(int products : shoppingCartController.getProductsFromCart().keySet()) {
                shopController.updateStock(products,shoppingCartController.getProductsFromCart().get(products));
            }
            dataSource.removeOrder(orderId,userName);
            removed=true;
        }
       return removed;
    }
    public boolean viewOrdersPlaced(String userName){
        boolean printed=false;
        ArrayList<Integer> orders=dataSource.readParticularOrder(userName);
        HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(userName);
        for(int orderId:orders) {
            if(!(orderStatus.get(orderId).equals("Delivered"))){
                displayOrderDetails(orderId,userName,orderStatus.get(orderId));
                printed=true;
            }
        }
        return printed;
    }
    public boolean viewPastOrdersPlaced(String userName) {
        boolean printed=false;
        ArrayList<Integer>orders=dataSource.readParticularOrder(userName);
        HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(userName);
        for(int orderId:orders) {
            displayOrderDetails(orderId,userName,orderStatus.get(orderId));
            printed=true;
        }
        return printed;
    }
    public boolean viewAllOrdersPlaced() {
        boolean printed=false;
        ArrayList<Integer>orders=dataSource.readAllOrders();
        for(int orderId:orders) {
            String userName=dataSource.getUserFromOrderedUsers(orderId);
            HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(userName);
            displayOrderDetails(orderId,userName,orderStatus.get(orderId));
            printed=true;
        }
        return printed;
    }
    public boolean containsOrderId(int orderId){
        return dataSource.readAllOrders().contains(orderId);
    }
    public void dispatchOrder(int orderId) {
        dataSource.updateOrderStatus(dataSource.getUserFromOrderedUsers(orderId),orderId);
    }
    public void updateView(){
        OrderViewService orderView = new OrderView();
        orderView.printOrderDetails(getOrderId(),getCustomer(),getOrderedProducts(),getDeliveryDate(),getDeliveryStatus(),getBillAmount());
    }

}
