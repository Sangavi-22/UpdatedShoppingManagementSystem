package View.Seller;

import Inputs.InputsFromUser;
import Controller.ControlSeller.SellerAccountControllerService;

public class SellerProfileDetailsView implements SellerProfileDetailsViewService{
    private String name,email;
    private long mobileNum;
    private SellerAccountControllerService sellerAccountController;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public SellerProfileDetailsView(SellerAccountControllerService sellerAccountController){
        this.sellerAccountController=sellerAccountController;
    }
    public void inputSellerDetails(){
        name = getNameFromUser();
        email = getEmailFromUser();
        mobileNum = getMobileNumFromUser();
        passDetailsToController();
    }
    public void passDetailsToController(){
        sellerAccountController.setNewSellerInfo(name,email,mobileNum);
    }
    public void displayEditProfileOptions(){
        System.out.println("1.name  2.email  3.mobileNum  4.save changes");
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
}
