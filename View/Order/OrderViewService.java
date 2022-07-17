package View.Order;

import java.time.LocalDate;
import java.util.HashMap;

public interface OrderViewService{
    void printOrderDetails(int orderId, String customer, HashMap<Integer,Integer>orderedProducts, LocalDate deliveryDate,String deliveryStatus,int billAmount);
}
