package Controller.ControlCustomer;

import Controller.ControlOrder.OrderDetails.OrderController;
import Controller.ControlOrder.OrderDetails.OrderControllerService;
import Payment.*;
import DataStorage.*;
import Model.ShoppingCart.ShoppingCart;
import Model.Users.Customer;
import View.Customer.*;
import Controller.ControlShop.*;
import Controller.ControlCart.*;
import Controller.ControlLogin.*;

public class CustomerAccountController implements CustomerAccountControllerService {
    private final String userName,password;
    private int quantity,userChoice;
    private Customer customer;
    private final CustomerProfileDetailsViewService customerProfileDetailsView=new CustomerProfileDetailsView(this);
    private final CustomerMenuViewService customerMenuView=new CustomerMenuView();
    private final PrintCustomerInfoViewService printCustomerInfoView=new PrintCustomerInfoView();
    private final ShoppingCart model =new ShoppingCart();
    private final ShoppingCartControllerService shoppingCartController=new ShoppingCartController(model);
    private final ShopControllerService shopController=new ShopController();
    private final DataSourceService dataSource= DataSource.getInstance();
    private final LoginControllerService loginController=new LoginController();
    private final OrderControllerService orderController=new OrderController();
    public CustomerAccountController(String userName,String password) {
        this.userName=userName;
        this.password=password;
    }
    public void inputCustomerInfo(){
        customerProfileDetailsView.inputCustomerDetails();
    }
    public void setNewCustomerInfo(String name,String email,long mobileNum,String address){
        customer=new Customer(userName,password,name,email,mobileNum,address);
        addCustomerDetailsToDataSource();
    }
    public void addCustomerDetailsToDataSource(){
        dataSource.addCustomer(customer);
    }
    public void setAlreadyExistingCustomerInfo(){
        customer=dataSource.getCustomer(userName);
    }
    public void customerMenu() {
        try {
            customerMenuView.displayMenuPage();
            userChoice = customerMenuView.getChoiceOfUser();
            int productId;
            switch (CustomerActions.values()[userChoice - 1]) {
                case VIEW_PRODUCTS:
                    boolean viewProducts = true;
                    while (viewProducts) {
                        if(displayedProductsInShop()) {
                            boolean wantToViewInDetail = customerMenuView.confirmViewParticularProduct();
                            if(wantToViewInDetail) {
                                productId = customerMenuView.getProductId();
                                shopController.displayParticularProduct(productId);
                                if(customerMenuView.confirmAddToCart() && shopController.containsProduct(productId)) {
                                    addToCart(productId);
                                }
                                viewProducts=goBackToList();
                            }
                            else {
                                customerMenuView.displayMessage();
                                viewProducts=false;
                            }
                        }
                        else {
                            customerMenuView.displayMessage();
                            viewProducts=false;
                        }
                    }
                    customerMenu();
                    break;
                case VIEW_CART:
                    if(displayedCart() && !isAGuestUser()) {
                        if(customerMenuView.confirmPlaceOrder()){
                            int amount = orderController.addToOrders(shoppingCartController.getShoppingCart(userName), userName);
                            if(amount > 0){
                                boolean iterate = true;
                                while(iterate) {
                                    iterate=false;
                                    boolean change = customerMenuView.confirmShippingAddress(customer.getShippingAddress());
                                    if(change){
                                        String shippingAddress = customerMenuView.inputAddressToChange();
                                        customer.setShippingAddress(shippingAddress);
                                        iterate=true;
                                    }
                                }
                                PaymentOperation payment;
                                int choice = customerMenuView.displayPaymentSection();
                                switch (PaymentType.values()[choice - 1]) {
                                    case CARD:
                                        String cardNumber = customerMenuView.getCardNumber();
                                        payment = new Card();
                                        payment.transferAmount(cardNumber);
                                        break;
                                    case GOOGLE_PAY:
                                        String googlePayId = customerMenuView.getGooglePayId();
                                        payment = new GooglePay();
                                        payment.transferAmount(googlePayId);
                                        break;
                                    case CASH_ON_DELIVERY:
                                        customerMenuView.confirmCODOption();
                                }
                                customerMenuView.displayOrderPlaced();
                            }
                        }
                        else {
                            customerMenuView.displayMessage();
                        }
                    }
                    customerMenu();
                    break;
                case REMOVE_FROM_CART:
                    if(displayedCart()) {
                        boolean remove = customerMenuView.confirmRemoveFromCart();
                        if(remove) {
                            productId = customerMenuView.getProductId();
                            shoppingCartController.removeProductFromCart(userName, productId);
                            customerMenuView.displayProductRemovedFromCart();
                        }
                        else {
                            customerMenuView.displayMessage();
                        }
                    }
                    customerMenu();
                    break;
                case UPDATE_QUANTITY:
                    if(displayedCart()) {
                        boolean update = customerMenuView.confirmUpdateProductQuantity();
                        productId = customerMenuView.getProductId();
                        customerMenuView.displayEnterNewQuantity();
                        quantity = customerMenuView.getQuantity();
                        if(update && shopController.containsProduct(productId)) {
                            if(shopController.phoneAvailable(productId, quantity) && shoppingCartController.productInCartAlready(userName, productId)) {
                                shoppingCartController.updateProductQuantityInCart(userName, productId, quantity);
                                customerMenuView.displayProductQuantityUpdated();
                            }
                            else {
                                customerMenuView.displayCannotUpdateQuantity();
                            }
                        }
                        else {
                            customerMenuView.displayMessage();
                        }
                    }
                    customerMenu();
                    break;
                case SEARCH_PRODUCTS:
                    boolean search=true;
                    String inputToSearch = customerMenuView.getInput();
                    while(search) {
                        if (!shopController.searchProduct(inputToSearch)) {
                            customerMenuView.displayNoProducts();
                            search=false;
                        }
                        else {
                            boolean wantToViewInDetail = customerMenuView.confirmViewParticularProduct();
                            if(wantToViewInDetail) {
                                productId = customerMenuView.getProductId();
                                if(shopController.containsProduct(productId)) {
                                    shopController.displayParticularProduct(productId);
                                    if(customerMenuView.confirmAddToCart()) {
                                        addToCart(productId);
                                    }
                                    search=goBackToList();
                                }
                                else {
                                    customerMenuView.displayMessage();
                                    search=false;
                                }
                            }
                            else {
                                customerMenuView.displayMessage();
                                search=false;
                            }
                        }
                    }
                    customerMenu();
                    break;
                case TRACK_ORDERS:
                    if(!(isAGuestUser())) {
                        if(!(orderController.viewOrdersPlaced(customer.getUserName()))) {
                            customerMenuView.displayNoOrders();
                        }
                        else {
                            customerMenuView.displayMessage();
                        }
                    }
                    customerMenu();
                    break;
                case REMOVE_AN_ORDER:
                    int orderId;
                    if(!(isAGuestUser())) {
                        if(!(orderController.viewOrdersPlaced(customer.getUserName()))) {
                            customerMenuView.displayNoOrders();
                        }
                        else {
                            orderId = customerMenuView.getOrderId();
                            if(orderController.removeOrder(orderId, customer.getUserName())) {
                                customerMenuView.displayOrderRemoved();
                            }
                            else {
                                customerMenuView.displayNoOrders();
                            }
                        }
                    }
                    customerMenu();
                    break;
                case VIEW_PAST_ORDERS:
                    if(!(isAGuestUser())) {
                        if(!(orderController.viewPastOrdersPlaced(customer.getUserName()))) {
                            customerMenuView.displayNoOrders();
                        }
                        else {
                            customerMenuView.displayMessage();
                        }
                    }
                    customerMenu();
                    break;
                case VIEW_PROFILE:
                    if(!(isAGuestUser())) {
                        updateView();
                    }
                    customerMenu();
                    break;
                case UPDATE_PROFILE:
                    if(!(isAGuestUser())) {
                        updateProfileDetails();
                        updateView();
                    }
                    customerMenu();
                    break;
                case VIEW_SHOP_DETAILS:
                    shopController.updateView();
                    customerMenu();
                    break;
                case EXIT:
                    shoppingCartController.writeProductsFromCartToDataSource(userName);
                    loginController.logOut(userName);
                    break;
                case DEFAULT:
                    customerMenuView.displayInvalidInput();
                    customerMenuView.displayMessage();
                    customerMenu();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException exception){
            customerMenuView.displayDefaultMessage();
            customerMenu();
        }
    }
    public boolean displayedProductsInShop(){
        boolean displayed=true;
        if (!(shopController.listProductsInShop())) {
            customerMenuView.displayNoProducts();
            displayed=false;
        }
        return displayed;
    }
    public boolean goBackToList(){
        boolean goBackToList=true;
        if(!(customerMenuView.goBackToList())){
            customerMenuView.displayMessage();
            goBackToList=false;
        }
        return goBackToList;
    }
    public boolean displayedCart(){
        boolean displayed=true;
        if (!(shoppingCartController.displayCart(userName))) {
            customerMenuView.displayNoProducts();
            displayed=false;
        }
        return displayed;
    }
    public void addToCart(int productId){
        if(shoppingCartController.productInCartAlready(userName,productId)){
            customerMenuView.displayProductPresentInCart();
        }
        else{
            quantity=customerMenuView.getQuantity();
            shoppingCartController.addProductsToCart(userName,productId,quantity);
            customerMenuView.displayProductAddedToCart();
        }
    }
    public boolean isAGuestUser(){
        boolean guestUser=false;
        if(userName.equals("guest")) {
            guestUser=true;
            guestUser();
        }
        return guestUser;
    }
    public void guestUser(){
        boolean createAccountForGuestUser = customerMenuView.displayMessageForGuest();
        if(createAccountForGuestUser) {
            loginController.createAccountForGuest();
        }
        else {
            customerMenuView.displayMessage();
        }
    }
    public void updateProfileDetails() {
        boolean update = true;
        while (update) {
            customerProfileDetailsView.displayEditProfileOptions();
            userChoice = customerMenuView.getChoiceOfUser();
            switch (UpdateCustomerDetailsOptions.values()[userChoice - 1]) {
                case UPDATE_NAME:
                    String name = customerProfileDetailsView.getNameFromUser();
                    customer.setName(name);
                    break;
                case UPDATE_EMAIL:
                    String email = customerProfileDetailsView.getEmailFromUser();
                    customer.setEmail(email);
                    break;
                case UPDATE_MOBILE_NUMBER:
                    long mobileNum = customerProfileDetailsView.getMobileNumFromUser();
                    customer.setMobileNum(mobileNum);
                    break;
                case UPDATE_ADDRESS:
                    String address = customerProfileDetailsView.getAddressFromUser();
                    customer.setShippingAddress(address);
                    break;
                case SAVE_DETAILS:
                    dataSource.updateCustomerDetailsInDataSource(customer);
                    update = false;
                    break;
            }
        }
    }
    public void updateView(){
        printCustomerInfoView.printCustomerDetails(customer.getName(),customer.getEmail(),customer.getMobileNum(),customer.getShippingAddress());
    }
}

                          
