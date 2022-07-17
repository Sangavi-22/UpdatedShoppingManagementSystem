package Controller.ControlOrder.CalculateDeliveryDateUtil;

import java.time.LocalDate;

public class DeliveryDate {
    private LocalDate deliveryDate;
    public void setDeliveryDate(int orderId){
        this.deliveryDate=LocalDate.now().plusDays(orderId);
    }
    public LocalDate getDeliveryDate(){
        return this.deliveryDate;
    }
}
