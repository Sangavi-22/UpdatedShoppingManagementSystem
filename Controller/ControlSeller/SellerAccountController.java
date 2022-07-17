package Controller.ControlSeller;

import Controller.ControlOrder.OrderDetails.OrderController;
import Controller.ControlOrder.OrderDetails.OrderControllerService;
import DataStorage.*;
import Model.Users.Seller;
import View.Seller.*;
import Controller.ControlShop.*;
import Controller.ControlLogin.*;

public class SellerAccountController implements SellerAccountControllerService{
    private final String userName,password;
    private int productId,userChoice;
    private Seller seller;
    private final SellerProfileDetailsViewService sellerProfileDetailsView;
    private final SellerMenuViewService sellerMenuView;
    private final PrintSellerInfoViewService printSellerInfoView;
    private final DataSourceService dataSource=DataSource.getInstance();
    private final ShopControllerService shopController=new ShopController();
    private final OrderControllerService orderController=new OrderController();
    public SellerAccountController(String userName,String password) {
        this.userName=userName;
        this.password=password;
        sellerProfileDetailsView=new SellerProfileDetailsView(this);
        sellerMenuView=new SellerMenuView();
        printSellerInfoView=new PrintSellerInfoView();
    }
    public void inputSellerInfo(){
        sellerProfileDetailsView.inputSellerDetails();
    }
    public void setNewSellerInfo(String name,String email,long mobileNum){
        seller=new Seller(userName,password,name,email,mobileNum);
        addSellerDetailsToDataSource();
    }
    public void addSellerDetailsToDataSource(){
        dataSource.addSeller(seller);
    }
    public void setAlreadyExistingSellerInfo(){
        seller=dataSource.getSeller(userName);
    }
    public void sellerMenu() {
        try {
            sellerMenuView.displayMenuPage();
            userChoice = sellerMenuView.getChoiceOfUser();
            switch(SellerMenuOptions.values()[userChoice - 1]) {
                case VIEW_PRODUCTS:
                    boolean viewProducts = true;
                    while(viewProducts){
                        if(displayedProductsInShop()) {
                            boolean wantToViewInDetail = sellerMenuView.confirmViewParticularProduct();
                            if(wantToViewInDetail) {
                                productId= sellerMenuView.getProductId();
                                shopController.displayParticularProduct(productId);
                                viewProducts=goBackToList();
                            }
                            else {
                                sellerMenuView.displayMessage();
                                viewProducts=false;
                            }
                        }
                        else {
                            viewProducts=false;
                        }
                    }
                    sellerMenu();
                    break;
                case ADD_PRODUCTS:
                    int added= shopController.addProductToShop();
                    if(added == 1) {
                        sellerMenuView.displayProductAdded();
                    }
                    else if(added == 2) {
                        sellerMenuView.displayProductPresentAlready();
                        sellerMenuView.displayQuantityUpdated();
                    }
                    else{
                        sellerMenuView.displayProductCannotBeAdded();
                    }
                    sellerMenu();
                    break;
                case UPDATE_PRODUCT_QUANTITY:
                    if(displayedProductsInShop()) {
                        updateProductQuantity();
                    }
                    sellerMenu();
                    break;
                case REMOVE_PRODUCTS:
                    if(displayedProductsInShop()) {
                        int removeProduct=sellerMenuView.displayRemoveProductOptions();
                        if(removeProduct== 1) {
                            removeProductWithId();
                        }
                        else if(removeProduct== 2) {
                            String modelName = sellerMenuView.getProductName();
                            if(shopController.removeProductWithName(modelName)) {
                                removeProductWithId();
                            }
                            else {
                                sellerMenuView.displayMessage();
                            }
                        }
                        else {
                            sellerMenuView.displayMessage();
                        }
                    }
                    sellerMenu();
                    break;
                case SEARCH_PRODUCTS:
                    boolean search = true;
                    String inputToSearch = sellerMenuView.getUserInputToSearch();
                    while (search) {
                        if (!shopController.searchProduct(inputToSearch)) {
                            sellerMenuView.displayNoProducts();
                            search=false;
                        }
                        else {
                            boolean wantToView = sellerMenuView.confirmViewParticularProduct();
                            if(wantToView) {
                                productId= sellerMenuView.getProductId();
                                shopController.displayParticularProduct(productId);
                                search=goBackToList();
                            }
                            else{
                                sellerMenuView.displayMessage();
                                search=false;
                            }
                        }
                    }
                    sellerMenu();
                    break;
                case VIEW_ORDERS:
                    int orderId;
                    if(!(orderController.viewAllOrdersPlaced())) {
                        sellerMenuView.displayNoRecords();
                    }
                    else if(sellerMenuView.confirmDispatchOrder()) {
                        orderId = sellerMenuView.getOrderId();
                        if(orderController.containsOrderId(orderId)) {
                            orderController.dispatchOrder(orderId);
                            sellerMenuView.displayOrderDispatched(orderId);
                        }
                        else {
                            sellerMenuView.displayNoRecords();
                        }

                    }
                    else {
                        sellerMenuView.displayMessage();
                    }
                    sellerMenu();
                    break;
                case CHECK_STOCK:
                    if(!(shopController.checkStock())) {
                        sellerMenuView.displayNoRecords();
                    }
                    else {
                        updateProductQuantity();
                    }
                    sellerMenu();
                    break;
                case VIEW_PROFILE:
                    updateView();
                    sellerMenu();
                    break;
                case UPDATE_PROFILE:
                    updateProfileDetails();
                    updateView();
                    sellerMenu();
                    break;
                case LOGOUT:
                    LoginControllerService loginController = new LoginController();
                    loginController.logOut(seller.getUserName());
                    break;
                case DEFAULT:
                    sellerMenuView.displayInvalidInput();
                    sellerMenuView.displayMessage();
                    sellerMenu();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException|NullPointerException exception){
                sellerMenuView.displayDefaultMessage();
                sellerMenu();
        }
    }
    public boolean displayedProductsInShop(){
        boolean displayed=true;
        if (!(shopController.listProductsInShop())) {
            sellerMenuView.displayNoProducts();
            displayed=false;
        }
        return displayed;
    }
    public boolean goBackToList(){
        boolean goBackToList=true;
        if(!(sellerMenuView.displayBackToList())){
            sellerMenuView.displayMessage();
            goBackToList=false;
        }
        return goBackToList;
    }
    public void removeProductWithId(){
        productId=sellerMenuView.getProductId();
        if(shopController.removeProductWithItsId(productId)) {
            sellerMenuView.displayProductRemoved();
        }
        else {
            sellerMenuView.displayNoRecords();
        }
    }
    public void updateProductQuantity(){
        int quantity;
        if(sellerMenuView.confirmUpdateProductQuantity()) {
            productId = sellerMenuView.getProductId();
            quantity = sellerMenuView.getQuantity();
            if(shopController.updateStock(productId, quantity) && quantity !=0) {
                sellerMenuView.displayQuantityUpdated();
            }
            else {
                sellerMenuView.displayCannotUpdate();
            }
        }
        else {
            sellerMenuView.displayMessage();
        }
    }
    public void updateProfileDetails() {
        boolean update=true;
        while(update){
            sellerProfileDetailsView.displayEditProfileOptions();
            userChoice=sellerMenuView.getChoiceOfUser();
            switch(UpdateSellerDetailsOptions.values()[userChoice-1]) {
                case UPDATE_NAME:
                    String name=sellerProfileDetailsView.getNameFromUser();
                    seller.setName(name);
                    break;
                case UPDATE_EMAIL:
                    String email=sellerProfileDetailsView.getEmailFromUser();
                    seller.setEmail(email);
                    break;
                case UPDATE_MOBILE_NUMBER:
                    long mobileNum=sellerProfileDetailsView.getMobileNumFromUser();
                    seller.setMobileNum(mobileNum);
                    break;
                case SAVE_DETAILS:
                    dataSource.updateSellerDetailsInDataSource(seller);
                    update=false;
                    break;
            }
        }
    }
    public void updateView(){
        printSellerInfoView.printSellerDetails(seller.getName(),seller.getEmail(),seller.getMobileNum());
    }
}
