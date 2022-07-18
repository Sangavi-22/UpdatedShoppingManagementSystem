package Model.MobilePhone;

public class Phone {
    private final PhoneSpecifications phoneSpecifications ;
    private final PhoneManufacturer phoneManufacturer;
    private final PhoneDimensions phoneDimensions;
    private final PhoneCamera phoneCamera;
    private final PhoneProcessor phoneProcessor;
    public Phone(PhoneSpecifications phoneSpecifications, PhoneManufacturer phoneManufacturer, PhoneDimensions phoneDimensions, PhoneCamera phoneCamera, PhoneProcessor phoneProcessor) {
        this.phoneSpecifications = phoneSpecifications;
        this.phoneManufacturer=phoneManufacturer;
        this.phoneDimensions=phoneDimensions;
        this.phoneCamera = phoneCamera;
        this.phoneProcessor = phoneProcessor;
    }
    public PhoneSpecifications getPhoneSpecifications(){
        return this.phoneSpecifications;
    }
    public PhoneManufacturer getPhoneManufacturer(){
        return this.phoneManufacturer;
    }
    public PhoneDimensions getPhoneDimensions(){
        return this.phoneDimensions;
    }
    public PhoneCamera getPhoneCamera(){
        return this.phoneCamera;
    }
    public PhoneProcessor getPhoneProcessor(){
        return this.phoneProcessor;
    }
}
