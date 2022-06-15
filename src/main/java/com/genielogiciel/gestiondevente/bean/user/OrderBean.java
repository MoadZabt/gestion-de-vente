package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Order;
import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.model.OrderModel;
import com.genielogiciel.gestiondevente.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "orderBean")
@ViewScoped
public class OrderBean implements Serializable {

    private OrderModel orderModel = new OrderModel();

    private UserService userService = new UserService();


    private List<Order> orders;

    public OrderBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
        User currentUser = (User) httpSession.getAttribute("currentUser");
        User user = userService.findById(currentUser.getId());
        orders = orderModel.findByUser(user);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
