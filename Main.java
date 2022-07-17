import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;

public class Main {
    public static void main(String[] args){
        LoginControllerService loginController=new LoginController();
        loginController.goToHomePage();
    }
}
