package Model.MobilePhone;

public class PhoneCamera {
    private final String primaryCamera,secondaryCamera;
    public PhoneCamera(String primaryCamera,String secondaryCamera){
        this.primaryCamera=primaryCamera;
        this.secondaryCamera=secondaryCamera;
    }
    public String getPrimaryCamera(){
        return this.primaryCamera;
    }
    public String getSecondaryCamera(){
        return this.secondaryCamera;
    }
}
