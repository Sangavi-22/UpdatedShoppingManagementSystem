package Admin;

import Controller.ControlLogin.AdminActions;

public interface AdminControlsService {
    void removeUser(String userName, AdminActions userType);
}
