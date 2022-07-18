package FileOperations;

import Model.MobilePhone.PhoneProcessor;

import java.util.HashMap;

public interface PhoneProcessorFileHandlerService {
    void writePhoneProcessorDetails(int productId, String processorType, String operatingSystem);
    HashMap<Integer, PhoneProcessor> readPhoneProcessorDetails();
    void removePhoneProcessor(int id);
}
