package FileOperations;

import Model.MobilePhone.PhoneManufacturer;

import java.util.HashMap;

public interface PhoneManufacturerFileHandlerService {
    void writePhoneManufacturerDetails(int productId, String manufacturerName);
    HashMap<Integer, PhoneManufacturer> readPhoneManufacturerDetails();
    void removePhoneManufacturer(int id);
}
