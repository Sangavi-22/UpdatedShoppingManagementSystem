package Model.MobilePhone;

public class PhoneSpecifications {
    private String modelName,batteryCapacity,displaySize;
    private int price;
    public PhoneSpecifications(String modelName,String batteryCapacity,String displaySize,int price){
        this.modelName=modelName;
        this.batteryCapacity=batteryCapacity;
        this.displaySize=displaySize;
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
    public int getPrice(){
        return this.price;
    }
}
