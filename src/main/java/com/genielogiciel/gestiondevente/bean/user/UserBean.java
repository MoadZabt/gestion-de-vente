package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.model.ProductModelGS;
import com.genielogiciel.gestiondevente.service.UserService;
import com.sun.net.httpserver.Authenticator;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userBean")
@ViewScoped
@ConversationScoped
@RequestScoped
public class UserBean implements Serializable {

    private User user;

    private List<User> users;

    private UserService userService = new UserService();

    private ProductModelGS productModel = new ProductModelGS();

    private List<Product> products;
    private String passwordConfirm;


    public UserBean() {
        users = userService.findAll();
        user = new User();
    }

    public String addUser() {
        System.out.println(user);
        userService.create(user);
        users.add(user);
        return "products.xhtml?faces-redirect=true";
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

    public String getPasswordConfirm() {
        System.out.println(passwordConfirm);
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    //validate login
    public String login() {
        String name = user.getUsername();

        String psw = user.getPassword();

        user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        try {
            if (name.equals(user.getUsername()) && psw.equals(user.getPassword())) {
                return "products.xhtml?faces-redirect=true";
            } else {

                return "login.xhtml?faces-redirect=true";

            }
        } catch (Exception e) {
            System.out.println(e);
            return "login.xhtml?faces-redirect=true";

        }
    }
    //to update user
    public void update(){
        long id =user.getId();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" +  id);
        user = userService.findById(1L);
        userService.update(user);

    }


}
