package View.Seller;

public interface SellerProfileDetailsViewService {
    void inputSellerDetails();
    void passDetailsToController();
    void displayEditProfileOptions();
    String getNameFromUser();
    String getEmailFromUser();
    long getMobileNumFromUser();
}
