package View.Customer;

public interface CustomerMenuViewService {
    void displayMenuPage();
    boolean goBackToList();
    void displayNoProducts();
    boolean confirmViewParticularProduct();
    boolean confirmAddToCart();
    void displayProductPresentInCart();
    void displayProductAddedToCart();
    boolean confirmRemoveFromCart();
    void displayProductRemovedFromCart();
    boolean confirmUpdateProductQuantity();
    void displayEnterNewQuantity();
    void displayProductQuantityUpdated();
    void displayCannotUpdateQuantity();
    boolean confirmPlaceOrder();
    boolean confirmShippingAddress(String shippingAddress);
    int displayPaymentSection();
    void confirmCODOption();
    void displayOrderPlaced();
    void displayOrderRemoved();
    void displayNoOrders();
    void displayInvalidInput();
    void displayMessage();
    void displayDefaultMessage();
    boolean displayMessageForGuest();
    String getInput();
    int getChoiceOfUser();
    int getProductId();
    int getQuantity();
    int getOrderId();
    String inputAddressToChange();
    String getGooglePayId();
    String getCardNumber();


}
