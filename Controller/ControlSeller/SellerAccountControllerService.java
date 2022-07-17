package Controller.ControlSeller;

public interface SellerAccountControllerService {
    void inputSellerInfo();
    void setNewSellerInfo(String name,String email,long mobileNum);
    void addSellerDetailsToDataSource();
    void setAlreadyExistingSellerInfo();
    void sellerMenu();
    boolean displayedProductsInShop();
    boolean goBackToList();
    void removeProductWithId();
    void updateProductQuantity();
    void updateProfileDetails();
    void updateView();
}
