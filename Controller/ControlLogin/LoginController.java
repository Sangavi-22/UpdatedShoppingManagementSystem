package Controller.ControlLogin;

import Admin.*;
import DataStorage.*;
import View.Login.*;
import Controller.ControlCustomer.*;
import Controller.ControlSeller.*;

public class LoginController implements LoginControllerService {
    private String userName,password;
    private final LoginViewService loginView=new LoginView();
    private final LoginUtility loginUtil = new LoginUtility();
    private final DataSourceService dataSource=DataSource.getInstance();
    public void goToHomePage() {
        try {
            loginView.displayHomePage();
            int choice = loginView.getChoiceOfUser();
            switch(HomePageOptions.values()[choice - 1])
            {
                case SIGN_UP:
                    createAccount();
                    goToHomePage();
                    break;
                case LOGIN:
                    loginToAccount();
                    goToHomePage();
                    break;
                case ADMIN:
                    adminLogin();
                    goToHomePage();
                    break;
                case GUEST:
                    guestUser();
                    break;
                case DEFAULT:
                    loginView.displayInvalidInput();
                    loginView.displayMessage();
                    goToHomePage();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException exception) {
            loginView.displayDefaultMessage();
            goToHomePage();
        }
    }
    public void createAccount(){
        loginView.displaySignUp();
        setSignUpDetails();
        int userType=loginView.confirmSeller();
        if(UserType.values()[userType-1].equals(UserType.SELLER)) {
            SellerAccountControllerService sellerAccountController=new SellerAccountController(userName,password);
            sellerAccountController.inputSellerInfo();
            sellerAccountController.sellerMenu();
        }
        else if(UserType.values()[userType-1].equals(UserType.CUSTOMER)) {
            CustomerAccountControllerService customerAccountController=new CustomerAccountController(userName,password);
            customerAccountController.inputCustomerInfo();
            customerAccountController.customerMenu();
        }
        else {
            loginView.displayCannotCreateAccount();
        }
    }
    public void loginToAccount(){
        loginView.displayLogin();
        setLoginDetails();
        if(loginUtil.isSellerPresentAlready(userName) && loginUtil.passwordMatchesForSeller(userName,password)) {
            SellerAccountControllerService sellerAccountController=new SellerAccountController(userName,password);
            sellerAccountController.setAlreadyExistingSellerInfo();
            sellerAccountController.sellerMenu();
        }
        else if(loginUtil.isCustomerPresentAlready(userName) && loginUtil.passwordMatchesForCustomer(userName,password)) {
            CustomerAccountControllerService customerAccountController=new CustomerAccountController(userName,password);
            customerAccountController.setAlreadyExistingCustomerInfo();
            customerAccountController.customerMenu();
        }
        else{
            loginView.displayNoAccount();
        }
    }
    public void adminLogin(){
        AdminControlsService adminControls=new AdminControls();
        loginView.displayAdminLogin();
        setLoginDetails();
        if(loginUtil.isAdminPresent(userName) && loginUtil.passwordMatchesForAdmin(userName,password)){
            boolean flag = true;
            while (flag) {
                loginView.displayAdminMenu();
                int userInput = loginView.getChoiceOfUser();
                switch(AdminActions.values()[userInput-1]) {
                    case REMOVE_SELLER:
                    case REMOVE_CUSTOMER:
                        this.userName = loginView.getUserName();
                        boolean userPresent = loginUtil.isSellerPresentAlready(userName) || loginUtil.isCustomerPresentAlready(userName);
                        adminControls.removeUser(userName,AdminActions.values()[userInput-1]);
                        if(userPresent && !(loginUtil.isSellerPresentAlready(userName))|| loginUtil.isCustomerPresentAlready(userName)) {
                            loginView.displayAccountRemoved();
                        }
                        else {
                            loginView.displayNoAccount();
                        }
                        break;
                    case EXIT:
                        flag=false;
                        break;
                }
            }
        }
        else {
            loginView.displayAccessDenied();
            loginView.displayMessage();
        }
    }
    public void guestUser(){
        CustomerAccountControllerService customerAccountController=new CustomerAccountController("guest","guest@123");
        customerAccountController.customerMenu();
    }
    public void createAccountForGuest(){
        loginView.displayCreateAccountForGuest();
        setLoginDetails();
        if(dataSource.containsCart("guest")) {
            dataSource.updateCartOwner(userName);
            dataSource.freeCartForUser("guest");
        }
        CustomerAccountControllerService customerAccountController=new CustomerAccountController(userName,password);
        customerAccountController.inputCustomerInfo();
        customerAccountController.customerMenu();
    }
    public void setSignUpDetails(){
        userName = loginView.getUserNameForFirstTime();
        verifyUserName();
        password =loginView.getPasswordForFirstTime();
    }
    public void setLoginDetails(){
        userName=loginView.getUserName();
        password=loginView.getPassword();
    }
    public void verifyUserName(){
        while(loginUtil.isSellerPresentAlready(userName) || loginUtil.isCustomerPresentAlready(userName)) {
            loginView.displayUserAlreadyPresent();
            userName = loginView.getUserName();
        }
    }
    public void logOut(String accountName) {
        if(accountName.equals("guest")) {
            loginView.displayThankYou();
        }
        else {
           loginView.displayLogOutMessage();
        }
        goToHomePage();
    }
}

