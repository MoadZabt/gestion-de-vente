package com.genielogiciel.gestiondevente.model;

import com.genielogiciel.gestiondevente.domain.Order;
import com.genielogiciel.gestiondevente.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderModel extends AbstractModel<Order> {

    public OrderModel(){
        super(Order.class);
    }

    public List<Order> findByDate(LocalDate date) {
        List<Order> orderInDate = new ArrayList<>();
        findAll().forEach(order -> {
            if(order.getShippingDate().isEqual(date)) {
                orderInDate.add(order);
            }
        });
        System.out.println(orderInDate);
        return orderInDate;
    }

    public List<Order> findByUser(User user) {
        List<Order> orderByUser = new ArrayList<>();
        findAll().forEach(order -> {
            if(Objects.equals(order.getUser().getId(), user.getId())) {
                orderByUser.add(order);
            }
        });
        return orderByUser;
    }
}
