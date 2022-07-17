package Model.Users;

public class Seller extends User{
    private String userName,password;
    public Seller(String userName, String password,String name, String email, long mobileNum) {
        super(name,email,mobileNum);
        this.userName=userName;
        this.password=password;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
}
