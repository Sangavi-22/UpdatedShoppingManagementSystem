package Controller.ControlOrder.OrderDetails;

import Model.ShoppingCart.ShoppingCart;
import java.time.LocalDate;
import java.util.HashMap;

public interface OrderControllerService {
    int getOrderId();
    String getCustomer();
    HashMap<Integer,Integer> getOrderedProducts();
    LocalDate getDeliveryDate();
    String getDeliveryStatus();
    int getBillAmount();
    void displayOrderDetails(int orderId,String userName,String deliveryStatus);
    int addToOrders(ShoppingCart shoppingCartModel, String userName);
    boolean removeOrder(int orderId, String userName);
    boolean viewOrdersPlaced(String userName);
    boolean viewPastOrdersPlaced(String userName);
    boolean viewAllOrdersPlaced();
    boolean containsOrderId(int orderId);
    void dispatchOrder(int orderId);
    void updateView();


}
