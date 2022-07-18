package FileOperations;

import Model.MobilePhone.PhoneSpecifications;
import java.util.HashMap;

public interface PhoneSpecificationsFileHandlerService {
    void writePhoneSpecifications(int productId, String modelName, String batteryCapacity, String displaySize,String storageCapacity,int price);
    int readLastPhoneId();
    HashMap<Integer, PhoneSpecifications> readPhoneSpecifications();
    void removePhoneSpecifications(int id);
}
