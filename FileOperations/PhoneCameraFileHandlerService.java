package FileOperations;

import Model.MobilePhone.PhoneCamera;

import java.util.HashMap;

public interface PhoneCameraFileHandlerService {
    void writePhoneCameraDetails(int productId, String primaryCamera, String secondaryCamera);
    HashMap<Integer, PhoneCamera> readPhoneCameraDetails();
    void removePhoneCamera(int id);
}
