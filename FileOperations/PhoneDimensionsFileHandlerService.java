package FileOperations;



import Model.MobilePhone.PhoneDimensions;

import java.util.HashMap;

public interface PhoneDimensionsFileHandlerService {
    void writePhoneDimensions(int productId, String width, String height, String weight);
    HashMap<Integer, PhoneDimensions> readPhoneDimensionsDetails();
    void removePhoneDimensions(int id);
}
