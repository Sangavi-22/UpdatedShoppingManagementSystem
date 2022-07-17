package Model.MobilePhone;

public class PhoneProcessor {
    String operatingSystem, processorType;
    public PhoneProcessor(String operatingSystem,String processorType){
        this.operatingSystem=operatingSystem;
        this.processorType=processorType;
    }
    public String getOperatingSystem(){
        return this.operatingSystem;
    }
    public String getProcessorType(){
        return this.processorType;
    }
}
