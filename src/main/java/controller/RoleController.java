package controller;

public interface RoleController {

    enum Role{
        ADMIN
    }

    void setRole(Role role);

}
