package View.Order;

import Controller.ControlMobilePhone.*;
import java.time.LocalDate;
import java.util.HashMap;

public class OrderView implements OrderViewService {
    public void printOrderDetails(int orderId, String customer, HashMap<Integer,Integer> orderedProducts, LocalDate deliveryDate,String deliveryStatus, int amount){
        System.out.println("Order Id: "+orderId);
        System.out.println("Order placed by: "+customer);
        PhoneControllerService phoneController=null;
        for (int products : orderedProducts.keySet()) {
            phoneController = new PhoneController();
            phoneController.getPhone(products);
            phoneController.printBasicInfo();
            phoneController.printOrderedQuantity(orderedProducts.get(products));
        }
        assert phoneController != null;
        phoneController.printDeliveryDateOfPhone(deliveryDate, deliveryStatus);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Bill Amount of orderId "+orderId+" is: Rs."+amount);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
