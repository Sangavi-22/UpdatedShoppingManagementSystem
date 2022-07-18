package Controller.ControlLogin;

public interface LoginControllerService {
    void goToHomePage();
    void createAccount();
    void loginToAccount();
    void adminLogin();
    void guestUser();
    void createAccountForGuest();
    void setSignUpDetails();
    void setLoginDetails();
    void verifyUserName();
    void logOut(String accountName);
}
