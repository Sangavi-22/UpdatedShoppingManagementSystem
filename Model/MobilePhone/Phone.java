package Model.MobilePhone;

public class Phone {
    private PhoneSpecifications phoneSpecifications ;
    private PhoneManufacturer phoneManufacturer;
    private PhoneDimensions phoneDimensions;
    private PhoneCamera phoneCamera;
    private PhoneProcessor phoneProcessor;
    private PhoneStorageCapacity phoneStorageCapacity;
    public Phone(PhoneSpecifications phoneSpecifications, PhoneManufacturer phoneManufacturer, PhoneDimensions phoneDimensions, PhoneCamera phoneCamera, PhoneProcessor phoneProcessor, PhoneStorageCapacity phoneStorageCapacity) {
        this.phoneSpecifications = phoneSpecifications;
        this.phoneManufacturer=phoneManufacturer;
        this.phoneDimensions=phoneDimensions;
        this.phoneCamera = phoneCamera;
        this.phoneProcessor = phoneProcessor;
        this.phoneStorageCapacity=phoneStorageCapacity;
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
    public PhoneStorageCapacity getPhoneStorageCapacity(){
        return this.phoneStorageCapacity;
    }
}
