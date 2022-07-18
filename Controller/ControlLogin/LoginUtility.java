package Controller.ControlLogin;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.Users.Customer;
import Model.Users.Seller;

public class LoginUtility {
    private final DataSourceService dataSource=DataSource.getInstance();
    public boolean isSellerPresentAlready(String userName){
        return dataSource.containsSeller(userName);
    }
    public boolean passwordMatchesForSeller(String userName,String password) {
        return dataSource.passwordOfSellerMatches(userName,password);
    }
    public boolean isCustomerPresentAlready(String userName){
        return dataSource.containsCustomer(userName);
    }
    public boolean passwordMatchesForCustomer(String userName,String password) {
        return dataSource.passwordOfCustomerMatches(userName,password);
    }
    public boolean isAdminPresent(String userName) {
        return dataSource.containsAdmin(userName);
    }
    public boolean passwordMatchesForAdmin(String userName,String password) {
        return dataSource.passwordOfAdminMatches(userName,password);
    }
}
