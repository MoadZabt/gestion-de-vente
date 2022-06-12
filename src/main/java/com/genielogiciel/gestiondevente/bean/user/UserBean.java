package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Product;
import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.model.ProductModelGS;
import com.genielogiciel.gestiondevente.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    private User user;

    private List<User> users;

    private UserService userService = new UserService();

    private ProductModelGS productModel = new ProductModelGS();

    private List<Product> products;


    public UserBean() {
        users = userService.findAll();
        user = new User();
        products = productModel.findAll();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
