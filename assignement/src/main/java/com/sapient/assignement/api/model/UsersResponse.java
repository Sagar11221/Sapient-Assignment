package com.sapient.assignement.api.model;

import java.util.List;

public class UsersResponse {
    private List<User> users;


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
