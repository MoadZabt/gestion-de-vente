package com.genielogiciel.gestiondevente.bean.user;

import com.genielogiciel.gestiondevente.domain.Order;
import com.genielogiciel.gestiondevente.domain.User;
import com.genielogiciel.gestiondevente.model.OrderModel;
import com.genielogiciel.gestiondevente.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "orderBean")
@ViewScoped
public class OrderBean implements Serializable {

    private OrderModel orderModel = new OrderModel();

    private UserService userService = new UserService();


    private List<Order> orders;

    public OrderBean() {
        User user = userService.findById(1L);
        orders = orderModel.findByUser(user);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
