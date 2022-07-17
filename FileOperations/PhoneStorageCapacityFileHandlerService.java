package FileOperations;

import Model.MobilePhone.PhoneStorageCapacity;

import java.util.HashMap;

public interface PhoneStorageCapacityFileHandlerService {
    void writePhoneProcessorDetails(int productId,String storageCapacity);

    HashMap<Integer, PhoneStorageCapacity> readPhoneStorageCapacityDetails();
    void removePhoneStorageCapacity(int id);
}
