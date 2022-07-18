package View.Customer;

import Inputs.InputsFromUser;
import Controller.ControlCustomer.CustomerAccountControllerService;

public class CustomerProfileDetailsView implements CustomerProfileDetailsViewService{
    private String name,email,address;
    private long mobileNum;
    private final CustomerAccountControllerService customerAccountController;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public CustomerProfileDetailsView(CustomerAccountControllerService customerAccountController){
        this.customerAccountController=customerAccountController;
    }
    public void inputCustomerDetails(){
        name =getNameFromUser();
        email = getEmailFromUser();
        mobileNum = getMobileNumFromUser();
        address=getAddressFromUser();
        passDetailsToController();
    }
    public void passDetailsToController(){
        customerAccountController.setNewCustomerInfo(name,email,mobileNum,address);
    }
    public void displayEditProfileOptions(){
        System.out.println("1.name  2.email  3.mobileNum  4.address  5.save changes");
    }
    public String getNameFromUser(){
        name = inputsFromUser.inputName();
        return name;
    }
    public String getEmailFromUser(){
        email=inputsFromUser.inputEmail();
        return email;
    }
    public long getMobileNumFromUser(){
        mobileNum=inputsFromUser.inputMobileNum();
        return mobileNum;
    }
    public String getAddressFromUser(){
        address=inputsFromUser.inputShippingAddress();
        return address;
    }

}
