package FileOperations;

import Model.Users.Seller;

import java.util.HashMap;

public interface SellerDetailsFileHandlerService {
    void writeSellerDetails(String userName,String password,String name,String email,long mobileNum);
    void updateSellerDetails(String userName, String password, String name, String email, long mobileNum);
    HashMap<String, Seller> readSellers();
    void removeSellerDetailsFromFile(String userName);

}
