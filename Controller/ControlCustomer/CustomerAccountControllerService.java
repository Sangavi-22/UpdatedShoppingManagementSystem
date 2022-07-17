package Controller.ControlCustomer;

import Payment.PaymentOperation;

public interface CustomerAccountControllerService {
    void inputCustomerInfo();
    void setNewCustomerInfo(String name, String email, long mobileNum, String address);
    void addCustomerDetailsToDataSource();
    void setAlreadyExistingCustomerInfo();
    void customerMenu();
    boolean displayedProductsInShop();
    boolean goBackToList();
    boolean displayedCart();
    void addToCart(int productId);
    boolean isAGuestUser();
    void guestUser();
    void updateProfileDetails();
    void updateView();
}
