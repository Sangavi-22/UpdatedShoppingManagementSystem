package View.Customer;

public interface CustomerProfileDetailsViewService {

    void inputCustomerDetails();
    void passDetailsToController();
    void displayEditProfileOptions();
    String getNameFromUser();
    String getEmailFromUser();
    long getMobileNumFromUser();
    String getAddressFromUser();
}
