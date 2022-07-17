package View.Login;

public interface LoginViewService {
    void displayHomePage();
    void displaySignUp();
    void displayLogin();
    void displayAdminLogin();
    void displayCreateAccountForGuest();
    void displayAdminMenu();
    void displayUserAlreadyPresent();
    int confirmSeller();
    void displayCannotCreateAccount();
    void displayNoAccount();
    void displayAccountRemoved();
    void displayAccessDenied();
    void displayThankYou();
    void displayLogOutMessage();
    void displayInvalidInput();
    void displayMessage();
    void displayDefaultMessage();
    int getChoiceOfUser();
    String getUserNameForFirstTime();
    String getUserName();
    String getPasswordForFirstTime();
    String getPassword();

}
