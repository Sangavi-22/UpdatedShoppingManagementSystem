package Controller.ControlMobilePhone;

import DataStorage.*;
import Model.MobilePhone.*;
import View.MobilePhone.*;
import java.time.LocalDate;

public class PhoneController implements PhoneControllerService{
    private final static int id=1;
    private int phoneId;
    private Phone phone;
    private final PhoneViewService phoneView=new PhoneView(this);
    private final PrintPhoneInfoViewService printPhoneInfoView=new PrintPhoneInfoView();
    private final DataSourceService dataSource= DataSource.getInstance();
    public void setInputs(){
        phoneView.inputPhoneDetails();
    }
    public int getProductIdForPhone(){
        if(dataSource.getLastPhoneId()!=0) {
            return dataSource.getLastPhoneId()+1;
        }
        else {
            return id;
        }
    }
    public void setPhoneId(int phoneId){
        this.phoneId=phoneId;
    }
    public int getPhoneId(){
        return this.phoneId;
    }
    public void setPhone(String modelName, String manufacturerName,String width, String height,String weight, String displaySize, String batteryCapacity, String primaryCamera, String secondaryCamera, String operatingSystem, String processorType, String storageCapacity, int price){
        setPhoneId(getProductIdForPhone());
        PhoneSpecifications phoneSpecifications=new PhoneSpecifications(modelName,batteryCapacity,displaySize,storageCapacity,price);
        PhoneManufacturer phoneManufacturer=new PhoneManufacturer(manufacturerName);
        PhoneDimensions phoneDimensions=new PhoneDimensions(width,height,weight);
        PhoneCamera phoneCamera=new PhoneCamera(primaryCamera,secondaryCamera);
        PhoneProcessor phoneProcessor=new PhoneProcessor(processorType,operatingSystem);
        phone=new Phone(phoneSpecifications,phoneManufacturer,phoneDimensions,phoneCamera,phoneProcessor);
    }
    public void addPhoneToDataSource(){
        dataSource.addPhoneSpecifications(phoneId,phone.getPhoneSpecifications());
        dataSource.addPhoneCamera(phoneId,phone.getPhoneCamera());
        dataSource.addPhoneDimensions(phoneId,phone.getPhoneDimensions());
        dataSource.addPhoneManufacturer(phoneId,phone.getPhoneManufacturer());
        dataSource.addPhoneProcessor(phoneId,phone.getPhoneProcessor());
    }
    public void getPhone(int phoneId){
        setPhoneId(phoneId);
        PhoneSpecifications phoneSpecifications = dataSource.getParticularPhone(phoneId);
        PhoneManufacturer phoneManufacturer = dataSource.getParticularPhoneWithManufacturer(phoneId);
        PhoneDimensions phoneDimensions = dataSource.getParticularPhoneWithDimensions(phoneId);
        PhoneCamera phoneCamera= dataSource.getParticularPhoneWithCamera(phoneId);
        PhoneProcessor phoneProcessor = dataSource.getParticularPhoneWithProcessor(phoneId);
        phone=new Phone(phoneSpecifications,phoneManufacturer,phoneDimensions,phoneCamera,phoneProcessor);
    }
    public String getModelName(){
        return phone.getPhoneSpecifications().getModelName();
    }
    public String getStorageCapacity(){
        return phone.getPhoneSpecifications().getStorageCapacity();
    }
    public int inputQuantityOfPhoneToAdd(){
        return phoneView.inputQuantityOfPhoneToAdd();
    }
    public void printQuantity(int quantity){
        printPhoneInfoView.printQuantityDetails(quantity);
    }
    public void printOrderedQuantity(int orderedQuantity){
        printPhoneInfoView.printOrderedQuantityDetails(orderedQuantity);
    }
    public void printDeliveryDateOfPhone(LocalDate deliveryDate, String deliveryStatus){
        printPhoneInfoView.printDeliveryDate(deliveryDate,deliveryStatus);
    }
    public void printBasicInfo(){
        printPhoneInfoView.printPhoneBasicInfo(getPhoneId(),phone.getPhoneSpecifications().getModelName(),phone.getPhoneSpecifications().getStorageCapacity(),phone.getPhoneSpecifications().getPrice());
    }
    public void updateView(){
        PhoneSpecifications phoneSpecifications=phone.getPhoneSpecifications();
        PhoneDimensions phoneDimensions=phone.getPhoneDimensions();
        PhoneManufacturer phoneManufacturer=phone.getPhoneManufacturer();
        PhoneCamera phoneCamera=phone.getPhoneCamera();
        PhoneProcessor phoneProcessor=phone.getPhoneProcessor();
        printPhoneInfoView.printPhoneDetails(getPhoneId(),phoneSpecifications.getModelName(),phoneManufacturer.getManufacturerName(),phoneDimensions.getWidth(),phoneDimensions.getHeight(),phoneDimensions.getWeight(),
                phoneSpecifications.getDisplaySize(),phoneSpecifications.getBatteryCapacity(),phoneCamera.getPrimaryCamera(),phoneCamera.getSecondaryCamera(),phoneProcessor.getOperatingSystem(),phoneProcessor.getProcessorType(),phoneSpecifications.getStorageCapacity(),phoneSpecifications.getPrice());
    }

}
