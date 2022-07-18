package View.Seller;

public interface SellerMenuViewService {
    void displayMenuPage();
    boolean displayBackToList();
    void displayNoProducts();
    boolean confirmViewParticularProduct();
    void displayProductAdded();
    void displayProductPresentAlready();
    void displayProductCannotBeAdded();
    boolean confirmUpdateProductQuantity();
    void displayQuantityUpdated();
    void displayCannotUpdate();
    int displayRemoveProductOptions();
    void displayProductRemoved();
    void displayNoRecords();
    boolean confirmDispatchOrder();
    void displayOrderDispatched(int orderId);
    void displayInvalidInput();
    void displayMessage();
    void displayDefaultMessage();
    int getChoiceOfUser();
    int getProductId();
    int getOrderId();
    String getProductName();
    String getUserInputToSearch();
    int getQuantity();

}
