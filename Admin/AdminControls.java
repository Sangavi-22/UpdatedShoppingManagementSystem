package Admin;

import Controller.ControlLogin.AdminActions;
import DataStorage.DataSource;
import DataStorage.DataSourceService;

public class AdminControls implements  AdminControlsService{
    private final DataSourceService dataSource= DataSource.getInstance();
    public void removeUser(String userName, AdminActions userType){
        if(userType.equals(AdminActions.REMOVE_SELLER)) {
            dataSource.removeSeller(userName);
        }
        else {
            dataSource.removeCustomer(userName);
        }
    }
}
