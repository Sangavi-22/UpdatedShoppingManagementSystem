package Model.Users;

public class Customer extends User{
    private final String userName,password;
    private String address;
    public Customer(String userName, String password, String name, String email, long mobileNum, String address) {
        super(name,email,mobileNum);
        this.userName=userName;
        this.password=password;
        this.address= address;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public void setShippingAddress(String address){
        this.address=address;
    }
    public String getShippingAddress(){
        return this.address;
    }
}
