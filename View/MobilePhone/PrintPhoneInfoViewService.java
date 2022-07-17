package View.MobilePhone;

import java.time.LocalDate;

public interface PrintPhoneInfoViewService {
    void printPhoneBasicInfo(int phoneId, String name, String storageCapacity, int price);
    void printPhoneDetails(int phoneId, String modelName, String manufacturerName, String width, String height, String weight, String displaySize, String batteryCapacity, String primaryCamera, String secondaryCamera, String operatingSystem, String processorType,String storageCapacity, int price);
    void printQuantityDetails(int quantity);
    void printOrderedQuantityDetails(int orderedQuantity);
    void printDeliveryDate(LocalDate deliveryDate, String deliveryStatus);

}
