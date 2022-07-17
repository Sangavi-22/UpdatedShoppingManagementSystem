package Model.MobilePhone;

public class PhoneDimensions {
    private String width,height,weight;
    public PhoneDimensions(String width,String height,String weight){
        this.width=width;
        this.height=height;
        this.weight=weight;
    }
    public String getWidth(){
        return this.width;
    }
    public String getHeight(){
        return this.height;
    }
    public String getWeight(){
        return this.weight;
    }
}
