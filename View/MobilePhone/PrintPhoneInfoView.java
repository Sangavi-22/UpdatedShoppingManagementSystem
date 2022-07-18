package View.MobilePhone;

import java.time.LocalDate;

public class PrintPhoneInfoView implements PrintPhoneInfoViewService{
    public void printPhoneBasicInfo(int phoneId, String name, String storageCapacity, int price){
        System.out.println("Phone Id: "+phoneId);
        System.out.println("Model Name: "+name);
        System.out.println("Storage Capacity: "+storageCapacity);
        System.out.println("Price: Rs."+price);
    }
    public void printPhoneDetails(int phoneId, String modelName, String manufacturerName, String width, String height, String weight, String displaySize, String batteryCapacity, String primaryCamera, String secondaryCamera, String operatingSystem, String processorType, String storageCapacity, int price){
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("!                    PHONE DETAILS                    !");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Phone Id: "+phoneId);
        System.out.println("ModelName: "+modelName);
        System.out.println("ManufacturerName: "+manufacturerName);
        System.out.println("Width: "+width);
        System.out.println("Height: "+height);
        System.out.println("Weight: "+weight);
        System.out.println("DisplaySize: "+displaySize);
        System.out.println("Battery Capacity: "+batteryCapacity);
        System.out.println("Primary Camera: "+primaryCamera);
        System.out.println("Secondary Camera: "+secondaryCamera);
        System.out.println("Operating System: "+operatingSystem);
        System.out.println("Processor Type: "+processorType);
        System.out.println("Storage Capacity: "+storageCapacity);
        System.out.println("Price: Rs."+price);
    }
    public void printQuantityDetails(int quantity){
        System.out.println("Stock Available: "+quantity);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }
    public void printOrderedQuantityDetails(int orderedQuantity){
        System.out.println("Ordered Quantity: "+orderedQuantity);
        System.out.println();
    }
    public void printDeliveryDate(LocalDate deliveryDate, String deliveryStatus){
        System.out.println("Delivery Date: "+deliveryDate);
        System.out.println("Delivery Status: "+deliveryStatus);
        System.out.println();
    }
}
