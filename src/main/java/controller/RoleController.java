package controller;

public interface RoleController {

    enum Role{
        ADMIN,
        MANAGER,
        SELLER,
        PROVIDER
    }

    void setRole(Role role);

}
