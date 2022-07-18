package FileOperations;

import Model.Users.Customer;

import java.util.HashMap;

public interface CustomerDetailsFileHandlerService {
    void writeCustomerDetails(String userName, String password, String name, String email, long mobileNum, String address);
    void updateCustomerDetails(String userName, String password, String name, String email, long mobileNum, String address);
    HashMap<String, Customer> readCustomers();
    void removeCustomerDetailsFromFile(String userName);
}
