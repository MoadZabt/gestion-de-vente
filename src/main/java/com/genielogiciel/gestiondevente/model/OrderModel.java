package com.genielogiciel.gestiondevente.model;

import com.genielogiciel.gestiondevente.domain.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModel extends AbstractModel<Order>{

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
}
