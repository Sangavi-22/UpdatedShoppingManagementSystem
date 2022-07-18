package Model.Order;

import java.time.LocalDate;
import java.util.HashMap;

public class Order {
    private final int orderId;
    private final int billAmount;
    private final String customer,deliveryStatus;
    private final LocalDate deliveryDate;
    private final HashMap<Integer,Integer>orderedProducts;

    public Order(int orderId, String customer, HashMap<Integer,Integer>orderedProducts, LocalDate deliveryDate, String deliveryStatus, int billAmount){
        this.orderId=orderId;
        this.customer=customer;
        this.orderedProducts=orderedProducts;
        this.deliveryDate=deliveryDate;
        this.deliveryStatus=deliveryStatus;
        this.billAmount=billAmount;
    }
    public int getOrderId(){
        return this.orderId;
    }
    public String getCustomer(){
        return this.customer;
    }
    public HashMap<Integer,Integer>getOrderedProducts(){
        return this.orderedProducts;
    }
    public LocalDate getDeliveryDate(){
        return this.deliveryDate;
    }
    public String getDeliveryStatus(){
        return this.deliveryStatus;
    }
    public int getBillAmount(){
        return this.billAmount;
    }

}
