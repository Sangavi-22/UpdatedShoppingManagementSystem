package Model.Users;

public class User {
    private String name,email;
    private long mobileNum;
    public User(String name,String email,long mobileNum){
        this.name=name;
        this.email=email;
        this.mobileNum=mobileNum;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setMobileNum(long mobileNum){
        this.mobileNum=mobileNum;
    }
    public long getMobileNum(){
        return this.mobileNum;
    }
}
