package controller;

public interface RoleController {

    enum Role{
        ADMIN,
        MANAGER,
        SELLER
    }

    void setRole(Role role);

}
