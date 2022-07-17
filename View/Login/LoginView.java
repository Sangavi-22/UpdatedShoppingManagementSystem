package View.Login;

import Inputs.InputsFromUser;

public class LoginView implements LoginViewService {
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void displayHomePage(){
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|            WELCOME TO MOBILE GALAXY STORE           |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|                 Enter 1 to SignUp                   |");
        System.out.println("|                 Enter 2 to Login                    |");
        System.out.println("|                 Enter 3 to Login as Admin           |");
        System.out.println("|                 Enter 4 to View as guest            |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }
    public void displaySignUp(){
        System.out.println("________________________SIGN UP________________________");
        System.out.println();
    }
    public void displayLogin(){
        System.out.println("_______________________LOGIN IN_______________________");
        System.out.println();
    }
    public void displayAdminLogin(){
        System.out.println("_________________________ADMIN_________________________");
        System.out.println();
    }
    public void displayCreateAccountForGuest(){
        System.out.println("__________CREATING AN ACCOUNT FOR GUEST USER_____________");
        System.out.println();
    }
    public void displayAdminMenu(){
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|                        WELCOME                      |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|                 Enter 1 to Remove Seller            |");
        System.out.println("|                 Enter 2 to Remove Customer          |");
        System.out.println("|                 Enter 3 to Exit                     |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public  void displayUserAlreadyPresent(){
        System.out.println("User already exist!!!!\n");
    }
    public int confirmSeller(){
        System.out.println("Are you a seller? 1.Yes 2.No\n");
        return getChoiceOfUser();
    }
    public void displayCannotCreateAccount(){
        System.out.println("Sorry account cannot be created\n");
    }
    public void displayNoAccount(){
        System.out.println("Sorry account does not exist\n");
    }
    public void displayAccountRemoved(){
        System.out.println("Account has been removed\n");
    }
    public void displayAccessDenied(){
        System.out.println("Unauthorized user\n");
    }
    public void displayThankYou(){
        System.out.println("Thank you for using our service\n");
    }
    public void displayLogOutMessage(){
        System.out.println("Thank you. You have been Logged out successfully\n");
    }
    public void displayInvalidInput(){
        System.out.println("Invalid input");
    }
    public void displayMessage() {
        System.out.println("Taking you back to the main page");
    }
    public void displayDefaultMessage(){
        System.out.println(" Some problem occurred !!!");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("! Please wait while we take you back to the main page !");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public int getChoiceOfUser(){
        return inputsFromUser.inputChoice();
    }
    public String getUserNameForFirstTime(){
        return inputsFromUser.inputUserNameFirstTime();
    }
    public String getUserName(){
        return inputsFromUser.inputUserName();
    }
    public String getPasswordForFirstTime(){
        return inputsFromUser.inputPasswordFirstTime();
    }
    public String getPassword(){
        return inputsFromUser.inputPassword();
    }

}
