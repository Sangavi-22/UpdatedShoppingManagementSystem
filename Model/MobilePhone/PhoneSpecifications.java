package Model.MobilePhone;

public class PhoneSpecifications {
    private final String modelName,batteryCapacity,displaySize,storageCapacity;
    private final int price;
    public PhoneSpecifications(String modelName,String batteryCapacity,String displaySize,String storageCapacity,int price){
        this.modelName=modelName;
        this.batteryCapacity=batteryCapacity;
        this.displaySize=displaySize;
        this.storageCapacity=storageCapacity;
        this.price=price;
    }
    public String getModelName(){
       return this.modelName;
    }
    public String getBatteryCapacity(){
        return this.batteryCapacity;
    }
    public String getDisplaySize(){
        return this.displaySize;
    }
    public String getStorageCapacity(){
        return this.storageCapacity;
    }
    public int getPrice(){
        return this.price;
    }
}
