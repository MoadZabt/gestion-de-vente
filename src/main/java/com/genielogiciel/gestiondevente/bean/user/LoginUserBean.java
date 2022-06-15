package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Product;
import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.service.UserService;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "loginUserBean")
@ApplicationScoped
public class LoginUserBean implements Serializable {

    private transient HttpSession httpSession;
    private User user;

    private User userToUpdate;
    private String passwordConfirm;
    private String password;

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginUserBean() {
        this.user = new User();
        this.httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public String getPasswordConfirm() {
        System.out.println(passwordConfirm);
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //validate login
    public String login() {
        String username = getUsername();
        String password = getPassword();

        User user = new User();
        UserService userService = new UserService();

        User userFound = userService.findByUsernameAndPassword(getUsername(), getPassword());
        if (userFound != null) {
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            httpSession.setAttribute("currentUser", userFound);
            return "products.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml?faces-redirect=true";
        }

    }

    public String logout() {
        this.httpSession.invalidate();
        return "login.xhtml?faces-redirect=true";
    }

    public String addUser() {
        UserService userService = new UserService();
        userService.create(user);
        System.out.println(user.getUsername());
        return "login.xhtml?faces-redirect=true";
    }

    public String updateUser() {
        this.userToUpdate = (User) this.httpSession.getAttribute("currentUser");
        UserService userService = new UserService();
        userService.update(userToUpdate);
        this.httpSession.setAttribute("currentUser",userToUpdate);
        return "products.xhtml?faces-redirect=true";
    }

    public User getUserToUpdate() {
        return userToUpdate;
    }

    public void setUserToUpdate(User userToUpdate) {
        this.userToUpdate = userToUpdate;
    }

}
