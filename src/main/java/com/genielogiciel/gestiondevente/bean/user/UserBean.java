package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    private User user;

    private List<User> users;

    @Inject
    private UserService userService;


    public UserBean() {
        users = userService.findAll();
        user = new User();
    }

    public void addUser() {
        userService.create(user);
        users.add(0, user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
