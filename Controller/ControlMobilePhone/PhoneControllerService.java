package Controller.ControlMobilePhone;

import Model.MobilePhone.Phone;
import java.time.LocalDate;

public interface PhoneControllerService {
    void setInputs();
    int getProductIdForPhone();
    void setPhoneId(int phoneId);
    int getPhoneId();
    void setPhone(String modelName, String manufacturerName, String width, String height,String weight, String displaySize, String batteryCapacity, String primaryCamera, String secondaryCamera, String operatingSystem, String processorType, String storageCapacity, int price);
    void addPhoneToDataSource();
    Phone getPhone(int phoneId);
    String getModelName();
    String getStorageCapacity();
    int inputQuantityOfPhoneToAdd();
    void printQuantity(int quantity);
    void printOrderedQuantity(int orderedQuantity);
    void printDeliveryDateOfPhone(LocalDate deliveryDate, String deliveryStatus);
    void printBasicInfo();
    void updateView();


}
