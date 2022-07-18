package View.MobilePhone;

import Inputs.InputsFromUser;
import Controller.ControlMobilePhone.PhoneControllerService;

public class PhoneView implements PhoneViewService{
    private String modelName,batteryCapacity,primaryCamera,secondaryCamera,manufacturerName,operatingSystem,processorType,width,height,weight,storageCapacity,displaySize;
    private int price;
    private final PhoneControllerService phoneController;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public PhoneView(PhoneControllerService phoneController){
        this.phoneController=phoneController;
    }
    public void inputPhoneDetails(){
        modelName=inputsFromUser.inputModelName();
        manufacturerName=inputsFromUser.inputManufacturer();
        width= inputsFromUser.inputWidth();
        height= inputsFromUser.inputHeight();
        weight=inputsFromUser.inputWeight();
        displaySize=inputsFromUser.inputDisplaySize();
        batteryCapacity=inputsFromUser.inputBatteryCapacity();
        primaryCamera=inputsFromUser.inputPrimaryCamera();
        secondaryCamera=inputsFromUser.inputSecondaryCamera();
        storageCapacity=inputsFromUser.inputStorageCapacity();
        System.out.println("Operating System: 1.Android 2.IOS");
        operatingSystem=inputsFromUser.inputChoice()==1?"Android":"IOS";
        processorType=inputsFromUser.inputProcessorType();
        price=inputsFromUser.inputPrice();
        passPhoneDetailsToController();
    }
    public void passPhoneDetailsToController(){
        phoneController.setPhone(modelName,manufacturerName,width,height,weight,displaySize,batteryCapacity,primaryCamera,secondaryCamera,operatingSystem,processorType,storageCapacity,price);
    }
    public int inputQuantityOfPhoneToAdd(){
        return inputsFromUser.inputCount();
    }
}
